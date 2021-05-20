package com.terheyden.valid;

/**
 * NotNullValidator class.
 */
public class NotNullValidator extends BaseNotNullValidator {

    @Override
    protected ValidateResult notNullValidate(ValidateContext context, Object value) {
        return ValidateResult.success(context);
    }
}
