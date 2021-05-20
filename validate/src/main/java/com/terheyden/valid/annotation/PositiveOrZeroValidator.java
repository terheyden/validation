package com.terheyden.valid.annotation;

import com.terheyden.valid.ValidateContext;
import com.terheyden.valid.ValidateResult;

/**
 * PositiveOrZero validator class.
 */
public class PositiveOrZeroValidator extends BaseNumberValidator {

    @Override
    protected ValidateResult numberValidate(ValidateContext context, Number number) {

        int intVal = number.intValue();

        return intVal >= 0
            ? ValidateResult.success(context)
            : ValidateResult.fail(context, "Number must be >= 0, but is actually: %s", intVal);
    }
}
