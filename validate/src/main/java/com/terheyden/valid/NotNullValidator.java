package com.terheyden.valid;

import javax.annotation.Nullable;

/**
 * NotNullValidator class.
 */
public class NotNullValidator implements Validator {

    @Override
    public ValidateResult validate(@Nullable Object val) {
        return val == null ? ValidateResult.fail("Value cannot be null") : ValidateResult.success();
    }
}
