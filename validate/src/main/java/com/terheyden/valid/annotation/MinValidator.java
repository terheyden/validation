package com.terheyden.valid.annotation;

import com.terheyden.valid.ValidateContext;

import io.vavr.Tuple;
import io.vavr.Tuple2;

/**
 * MinValidator class.
 */
public class MinValidator extends BaseSizeValidator {

    @Override
    protected Tuple2<Long, Long> getMinMax(ValidateContext context) {

        Min minAnn = context.annotation();
        long minVal = minAnn.value();

        return Tuple.of(minVal, Long.MAX_VALUE);
    }
}
