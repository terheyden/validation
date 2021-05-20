package com.terheyden.valid;

/**
 * BaseNumberValidator class.
 */
public abstract class BaseNumberValidator extends BaseNotNullValidator {

    @Override
    protected ValidateResult notNullValidate(ValidateContext context, Object value) {

        if (!(value instanceof Number num)) {
            return ValidateResult.fail(context, "Not a number type: %s", value.getClass().getSimpleName());
        }

        return numberValidate(context, num);
    }

    protected abstract ValidateResult numberValidate(ValidateContext context, Number number);
}
