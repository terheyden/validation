package com.terheyden.valid.annotation;

import com.terheyden.valid.ValidateContext;
import com.terheyden.valid.ValidateResult;

/**
 * PatternValidator class.
 */
public class PatternValidator extends StringValidator {

    @Override
    protected ValidateResult stringValidate(ValidateContext context, CharSequence strValue) {

        Pattern pattern = context.annotation();
        String regexStr = pattern.value();
        java.util.regex.Pattern regex = java.util.regex.Pattern.compile(regexStr);
        boolean isPatternFound = regex.matcher(strValue).matches();

        return isPatternFound
            ? ValidateResult.success(context)
            : ValidateResult.fail(context, "Value \"%s\" does not match pattern: \"%s\"", strValue, regexStr);
    }
}
