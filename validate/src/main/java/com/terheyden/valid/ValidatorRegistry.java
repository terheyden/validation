package com.terheyden.valid;

import javax.annotation.Nonnull;
import java.lang.annotation.Annotation;
import java.util.Optional;

import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.impl.factory.Maps;

import com.terheyden.valid.annotation.NotEmptyValidator;
import com.terheyden.valid.annotation.NotNullValidator;
import com.terheyden.valid.annotation.PositiveValidator;
import com.terheyden.valid.annotation.Validator;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * There are a fixed number of validation annotations, so I'm okay with
 * caching their validators here to minimize object usage.
 */
public final class ValidatorRegistry {

    private static final MutableMap<Class<? extends Validator>, Validator> VALIDATORS = Maps.mutable.empty();

    private static final ImmutableMap<Class<? extends Annotation>, Class<? extends Validator>>
        THIRD_PARTY_ANNOTATION_MAP = Maps.immutable.of(
            NotNull.class, NotNullValidator.class,
            Nonnull.class, NotNullValidator.class,
            NotEmpty.class, NotEmptyValidator.class,
            Positive.class, PositiveValidator.class);

    private ValidatorRegistry() {
        // Private since this class shouldn't be instantiated.
    }

    public static Validator getOrCreateValidator(ValidatedBy validatedBy) {
        Class<? extends Validator> validatorClass = validatedBy.value();
        return getOrCreateValidator(validatorClass);
    }

    private static Validator getOrCreateValidator(Class<? extends Validator> validatorClass) {
        return VALIDATORS.getIfAbsent(validatorClass, () -> ReflectionUtils.constructDefault(validatorClass));
    }

    public static Optional<Validator> find3rdPartyValidator(Class<? extends Annotation> annotationClass) {
        return Optional
            .ofNullable(THIRD_PARTY_ANNOTATION_MAP.get(annotationClass))
            .map(ValidatorRegistry::getOrCreateValidator);
    }
}
