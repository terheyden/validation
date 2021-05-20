package com.terheyden.valid.annotation;

import com.terheyden.valid.ValidateContext;

import io.vavr.Tuple;
import io.vavr.Tuple2;

/**
 * SizeValidator class for validating sizes of strings, collections, etc.
 */
public class SizeValidator extends BaseSizeValidator {

    // In case some users actually use Long.MIN_VALUE, we'll offset our number by a random little bit.
    private static final int SEED_OFFSET = 33;
    public static final long UNSET = Long.MIN_VALUE + SEED_OFFSET;

    @Override
    protected Tuple2<Long, Long> getMinMax(ValidateContext context) {

        Size sizeAnn = context.annotation();

        // E.g. @Size(3)
        long minMaxVal = sizeAnn.value();
        // E.g. @Size(min = 0)
        long minVal = sizeAnn.min();
        // E.g. @Size(max = 100)
        long maxVal = sizeAnn.max();

        // Interpret @Size(3) to mean @Size(min = 3, max = 3)
        if (minMaxVal != UNSET) {
            minVal = minMaxVal;
            maxVal = minMaxVal;
        }

        // If no minimum is set, default it to max min.
        if (minVal == UNSET) {
            minVal = Long.MIN_VALUE;
        }

        // If no max is set, default to long max.
        if (maxVal == UNSET) {
            maxVal = Long.MAX_VALUE;
        }

        return Tuple.of(minVal, maxVal);
    }
}
