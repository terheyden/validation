package com.terheyden.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collection;

import org.eclipse.collections.api.collection.ImmutableCollection;

/**
 * The @NotNullOrEmpty custom annotation validator.
 */
public class NotNullOrEmptyValidator implements ConstraintValidator<NotNullOrEmpty, Object> {

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {

        if (obj == null) {
            // I said no nulls!!
            return false;
        }

        if (obj instanceof Collection coll) {
            if (coll.isEmpty()) {
                return false;
            }
        } else if (obj instanceof ImmutableCollection imm) {
            if (imm.isEmpty()) {
                return false;
            }
        } else if (obj instanceof String str) {
            if (str.isEmpty()) {
                return false;
            }
        }

        // Looks valid to me.
        return true;
    }
}
