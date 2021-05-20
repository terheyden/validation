package com.terheyden.valid.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.terheyden.valid.ValidatedBy;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Marks a numeric field or method argument as needing to be at least this value.
 */
@Documented
@Retention(RUNTIME)
@ValidatedBy(SizeValidator.class)
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
public @interface Size {

    /**
     * By specifying only one value, e.g. {@code @Size(3)}, that value acts as
     * both the minimum and maximum allowed value(s).
     */
    long value() default SizeValidator.UNSET;

    /**
     * Minimum acceptable value(s).
     */
    long min() default SizeValidator.UNSET;

    /**
     * Max acceptable value(s).
     */
    long max() default SizeValidator.UNSET;
}
