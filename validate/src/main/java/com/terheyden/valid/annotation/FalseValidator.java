package com.terheyden.valid.annotation;

import com.terheyden.valid.ValidateContext;
import com.terheyden.valid.ValidateResult;

/**
 * Validates truthiness.
 */
public class FalseValidator implements Validator {

    @Override
    public ValidateResult validate(ValidateContext context) {

        Object value = context.value();

        return TruthUtils.isFalse(value)
            ? ValidateResult.success(context)
            : ValidateResult.fail(context, "Expected false-ish but was: %s", value);
    }
}
