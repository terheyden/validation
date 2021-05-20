package com.terheyden.valid.annotation;

import com.terheyden.valid.ValidateContext;
import com.terheyden.valid.ValidateResult;

import io.vavr.Tuple2;

/**
 * SizeValidator class for validating sizes of strings, collections, etc.
 */
public abstract class BaseSizeValidator implements Validator {

    @Override
    public ValidateResult validate(ValidateContext context) {

        Tuple2<Long, Long> minMax = getMinMax(context);
        long minVal = minMax._1();
        long maxVal = minMax._2();
        SizeChecker checker = new SizeChecker(minVal, maxVal);
        Object value = context.value();

        return checker.isValidSize(value)
            ? ValidateResult.success(context)
            : ValidateResult.fail(context, "Unexpected size: %d >= %d >= %d",
                minVal,
                value == null ? 0 : value,
                maxVal);
    }

    protected abstract Tuple2<Long, Long> getMinMax(ValidateContext context);
}
