package com.terheyden.valid;

/**
 * PositiveValidator class.
 */
public class PositiveValidator extends BaseNumberValidator {

    @Override
    protected ValidateResult numberValidate(ValidateContext context, Number number) {

        int intVal = number.intValue();

        return intVal > 0
            ? ValidateResult.success(context)
            : ValidateResult.fail(context, "Number must be greater than 0, but is actually: %s", intVal);
    }
}
