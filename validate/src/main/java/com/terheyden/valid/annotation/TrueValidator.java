package com.terheyden.valid.annotation;

import com.terheyden.valid.ValidateContext;
import com.terheyden.valid.ValidateResult;

/**
 * Validates truthiness.
 */
public class TrueValidator implements Validator {

    @Override
    public ValidateResult validate(ValidateContext context) {

        Object value = context.value();

        return TruthUtils.isTrue(value)
            ? ValidateResult.success(context)
            : ValidateResult.fail(context, "Expected true-ish value but was: %s", value);
    }
}
