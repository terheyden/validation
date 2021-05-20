package com.terheyden.valid.annotation;

import com.terheyden.valid.ValidateContext;
import com.terheyden.valid.ValidateResult;

/**
 * PatternValidator class.
 */
public class TrimValidator extends StringValidator {

    @Override
    protected ValidateResult stringValidate(ValidateContext context, CharSequence strValue) {

        Trim trimAnn = context.annotation();
        String regexStr = trimAnn.value();
        java.util.regex.Pattern regex = java.util.regex.Pattern.compile(regexStr);
        boolean isPatternFound = regex.matcher(strValue).matches();

        return isPatternFound
            ? ValidateResult.success(context)
            : ValidateResult.fail(context, "Value \"%s\" does not match pattern: \"%s\"", strValue, regexStr);
    }
}
