package com.terheyden.valid.annotation;

import com.terheyden.valid.ValidateContext;
import com.terheyden.valid.ValidateResult;

/**
 * BaseNotNullValidator class.
 */
public abstract class BaseNotNullValidator implements Validator {

    @Override
    public ValidateResult validate(ValidateContext context) {

        Object value = context.value();
        if (value == null) { return ValidateResult.fail(context, "Value cannot be null"); }

        return notNullValidate(context, value);
    }

    protected abstract ValidateResult notNullValidate(ValidateContext context, Object value);
}
