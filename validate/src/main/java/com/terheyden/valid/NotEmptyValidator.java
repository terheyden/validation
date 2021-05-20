package com.terheyden.valid;

import javax.annotation.Nullable;
import java.util.Collection;

/**
 * Validates tvalue is neither null nor empty.
 */
public class NotEmptyValidator implements Validator {

    @Override
    public ValidateResult validate(@Nullable Object val) {

        if (val == null) {
            return ValidateResult.fail("Value cannot be null");
        }

        if (val instanceof CharSequence str && str.isEmpty()) {
            return ValidateResult.fail("Value cannot be an empty string");
        } else if (val instanceof Collection coll && coll.isEmpty()) {
            return ValidateResult.fail("Value cannot be an empty collection");
        }

        return ValidateResult.success();
    }
}
