package com.terheyden.valid;

import java.lang.annotation.Annotation;
import java.util.Optional;

import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.impl.factory.Maps;

import com.terheyden.valid.annotation.FalseValidator;
import com.terheyden.valid.annotation.NotEmptyValidator;
import com.terheyden.valid.annotation.NotNullValidator;
import com.terheyden.valid.annotation.PositiveValidator;
import com.terheyden.valid.annotation.TrueValidator;
import com.terheyden.valid.annotation.Validator;

/**
 * There are a fixed number of validation annotations, so I'm okay with
 * caching their validators here to minimize object usage.
 */
public final class ValidatorRegistry {

    private static final MutableMap<Class<? extends Validator>, Validator> VALIDATORS = Maps.mutable.empty();

    private static final ImmutableMap<String, Class<? extends Validator>>
        THIRD_PARTY_ANNOTATION_MAP = init3rdPartyMap();

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
            .ofNullable(THIRD_PARTY_ANNOTATION_MAP.get(annotationClass.getCanonicalName()))
            .map(ValidatorRegistry::getOrCreateValidator);
    }

    /**
     * Creates our map of 3rd-party annotations and how to validate them.
     */
    private static ImmutableMap<String, Class<? extends Validator>> init3rdPartyMap() {

        MutableMap<String, Class<? extends Validator>> map = Maps.mutable.empty();

        map.put("jakarta.validation.constraints.NotEmpty", NotEmptyValidator.class);
        map.put("jakarta.validation.constraints.NotNull", NotNullValidator.class);
        map.put("jakarta.validation.constraints.Positive", PositiveValidator.class);
        map.put("jakarta.validation.constraints.AssertTrue", TrueValidator.class);
        map.put("jakarta.validation.constraints.AssertFalse", FalseValidator.class);
        map.put("javax.annotation.Nonnull", NotEmptyValidator.class);

        return map.toImmutable();
    }
}
