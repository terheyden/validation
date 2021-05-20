package com.terheyden.valid;

import javax.annotation.Nullable;
import java.lang.annotation.Annotation;

/**
 * ValidateContext class.
 */
public class ValidateContext {

    private final Annotation annotation;

    @Nullable private final Object value;

    public ValidateContext(Annotation annotation, Object value) {
        this.annotation = annotation;
        this.value      = value;
    }

    @SuppressWarnings("unchecked")
    public <T extends Annotation> T annotation() {
        return (T) annotation;
    }

    @Nullable
    public Object value() {
        return value;
    }
}
