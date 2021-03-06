package com.terheyden.valid.annotation;

import java.util.Collection;

import com.terheyden.valid.ValidateContext;
import com.terheyden.valid.ValidateResult;

/**
 * Validates the value is neither null nor empty.
 */
public class NotEmptyValidator extends BaseNotNullValidator {

    @Override
    protected ValidateResult notNullValidate(ValidateContext context, Object value) {

        if (value instanceof CharSequence str && str.isEmpty()) {
            return ValidateResult.fail(context, "Value cannot be an empty string");
        } else if (value instanceof Collection coll && coll.isEmpty()) {
            return ValidateResult.fail(context, "Value cannot be an empty collection");
        }

        return ValidateResult.success(context);
    }
}
