package com.terheyden.valid.annotation;

import com.terheyden.valid.ValidateContext;
import com.terheyden.valid.ValidateResult;

/**
 * MaxValidator class.
 */
public class MaxValidator extends BaseLongValidator {

    @Override
    protected ValidateResult longValidate(ValidateContext context, long number) {

        Max maxAnn = context.annotation();
        long maxVal = maxAnn.value();

        return number <= maxVal
            ? ValidateResult.success(context)
            : ValidateResult.fail(context, "Expected max value of %d, was actually: %d", maxVal, number);
    }
}
