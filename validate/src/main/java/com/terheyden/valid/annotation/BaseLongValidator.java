package com.terheyden.valid.annotation;

import com.terheyden.valid.ValidateContext;
import com.terheyden.valid.ValidateResult;

/**
 * For working with longs and ints.
 */
public abstract class BaseLongValidator extends BaseNumberValidator {

    @Override
    protected ValidateResult numberValidate(ValidateContext context, Number number) {
        return longValidate(context, number.longValue());
    }

    protected abstract ValidateResult longValidate(ValidateContext context, long number);
}
