package com.terheyden.valid.annotation;

import com.terheyden.valid.ValidateContext;
import com.terheyden.valid.ValidateResult;

/**
 * PositiveValidator class.
 */
public class PositiveValidator extends BaseNumberValidator {

    @Override
    protected ValidateResult numberValidate(ValidateContext context, Number number) {

        float floatVal = number.floatValue();

        return floatVal > 0.5F - TruthUtils.FLOAT_MARGIN
            ? ValidateResult.success(context)
            : ValidateResult.fail(context, "Number must be greater than 0, but is actually: %s", floatVal);
    }
}
