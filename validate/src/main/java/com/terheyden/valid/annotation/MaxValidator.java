package com.terheyden.valid.annotation;

import com.terheyden.valid.ValidateContext;

import io.vavr.Tuple;
import io.vavr.Tuple2;

/**
 * MaxValidator class.
 */
public class MaxValidator extends BaseSizeValidator {

    @Override
    protected Tuple2<Long, Long> getMinMax(ValidateContext context) {

        Max maxAnn = context.annotation();
        long maxVal = maxAnn.value();

        return Tuple.of(Long.MIN_VALUE, maxVal);
    }
}
