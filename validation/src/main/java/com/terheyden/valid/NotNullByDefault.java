package com.terheyden.valid;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.meta.TypeQualifierDefault;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import jakarta.validation.constraints.NotNull;

/**
 * Declares the following defaults:
 * Methods don't return null.
 * Method params are not null.
 * Fields are never set to null.
 *
 * Local vars can be null, sure, that's internal biz.
 *
 * This code was copied and modified from {@link ParametersAreNonnullByDefault}.
 *
 * See also:
 * package-info.java
 * https://stackoverflow.com/questions/13310994/set-findbugs-notnull-as-default-for-all-classes-under-a-package
 * https://stackoverflow.com/questions/3550292/what-do-java-annotation-elementtype-constants-mean
 */
@NotNull
@Nonnull
@Documented
@Retention(RetentionPolicy.RUNTIME)
@TypeQualifierDefault({ ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD })
public @interface NotNullByDefault {
    // No code goes here.
}
