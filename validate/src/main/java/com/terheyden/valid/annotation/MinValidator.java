package com.terheyden.valid.annotation;

import com.terheyden.valid.ValidateContext;
import com.terheyden.valid.ValidateResult;

/**
 * MinValidator class.
 */
public class MinValidator extends BaseLongValidator {

    @Override
    protected ValidateResult longValidate(ValidateContext context, long number) {

        Min minAnn = context.annotation();
        long minVal = minAnn.value();

        return number >= minVal
            ? ValidateResult.success(context)
            : ValidateResult.fail(context, "Expected minimum value of %d, was actually: %d", minVal, number);
    }
}
