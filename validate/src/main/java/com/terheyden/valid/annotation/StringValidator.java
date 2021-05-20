package com.terheyden.valid.annotation;

import com.terheyden.valid.ValidateContext;
import com.terheyden.valid.ValidateResult;

/**
 * Validates tvalue is neither null nor empty.
 */
public abstract class StringValidator extends BaseNotNullValidator {

    @Override
    protected ValidateResult notNullValidate(ValidateContext context, Object value) {

        if (!(value instanceof CharSequence str)) {
            return ValidateResult.fail(context, "Not a string type: %s", value.getClass().getSimpleName());
        }

        return stringValidate(context, str);
    }

    protected abstract ValidateResult stringValidate(ValidateContext context, CharSequence strValue);
}
