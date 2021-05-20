package com.terheyden.valid.annotation;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.collections.api.collection.ImmutableCollection;

import com.terheyden.valid.ValidateContext;
import com.terheyden.valid.ValidateResult;

import static java.lang.String.format;

/**
 * PatternValidator class. Works against strings and collections of strings.
 */
public class PatternValidator implements Validator {

    /**
     * If the value is null, you can refer to it in your pattern as 'null'.
     */
    public static final String NULL_VALUE = "null";
    private static final String EMPTY = "";

    @Override
    public ValidateResult validate(ValidateContext context) {

        Pattern pattern = context.annotation();
        String regexStr = pattern.value();
        java.util.regex.Pattern regex = java.util.regex.Pattern.compile(regexStr);

        // If the value is null, set it to 'null' instead.
        Object value = context.value();
        if (value == null) { value = NULL_VALUE; }

        String err;

        if (value instanceof Collection<?> coll) {
            err = validateAll(coll, regex);
        } else if (value instanceof ImmutableCollection<?> imm) {
            err = validateAll(imm.castToCollection(), regex);
        } else if (value instanceof Object[] arr) {
            err = validateAll(Arrays.asList(arr), regex);
        } else if (value instanceof Map<?, ?> map) {
            err = validateAll(map.keySet(), regex);
            if (err.equals(EMPTY)) {
                err = validateAll(map.values(), regex);
            }
        } else if (value instanceof CharSequence str) {
            err = validateAll(List.of(str), regex);
        } else {
            // Not something we could check!
            // Skip.
            return ValidateResult.success(context);
        }

        return EMPTY.equals(err)
            ? ValidateResult.success(context)
            : ValidateResult.fail(context, err);
    }

    /**
     * Validate the list of strings. If any don't match the regex,
     * return an ERROR MESSAGE.
     * @return empty string if successful, else an error string
     */
    private static String validateAll(Collection<?> list, java.util.regex.Pattern regex) {

        for (Object listVal : list) {

            if (!(listVal instanceof CharSequence strVal)) {
                continue;
            }

            if (!regex.matcher(strVal).matches()) {
                return format("Value \"%s\" does not match pattern: \"%s\"", strVal, regex.pattern());
            }
        }

        // They all match!
        return EMPTY;
    }
}
