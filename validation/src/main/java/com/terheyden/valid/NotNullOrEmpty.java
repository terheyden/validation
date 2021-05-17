package com.terheyden.valid;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Collection;

import com.terheyden.valid.NotNullOrEmpty.List;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Validate that a {@link String} or {@link Collection} is neither {@code null} nor empty.
 */
@Documented
@Retention(RUNTIME)
@Repeatable(List.class)
@Target({ FIELD, PARAMETER, ANNOTATION_TYPE, TYPE_USE })
@Constraint(validatedBy = NotNullOrEmptyValidator.class)
public @interface NotNullOrEmpty {

    String message() default "must not be null or empty";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
    //EnumOrString value(); <-- if you want an annotation arg

    /**
     * Allows this annotation to be used several times with different groupings.
     */
    @Documented
    @Retention(RUNTIME)
    @Target({ FIELD, PARAMETER, ANNOTATION_TYPE, TYPE_USE })
    @interface List {
        NotNullOrEmpty[] value();
    }
}
