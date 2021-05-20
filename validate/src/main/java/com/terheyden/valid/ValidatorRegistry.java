package com.terheyden.valid;

import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.impl.factory.Maps;

/**
 * There are a fixed number of validation annotations, so I'm okay with
 * caching their validators here to minimize object usage.
 */
public final class ValidatorRegistry {

    private static final MutableMap<Class<? extends Validator>, Validator> VALIDATORS = Maps.mutable.empty();

    private ValidatorRegistry() {
        // Private since this class shouldn't be instantiated.
    }

    public static Validator getOrCreateValidator(ValidatedBy validatedBy) {
        Class<? extends Validator> validatorClass = validatedBy.value();
        return VALIDATORS.getIfAbsent(validatorClass, () -> ReflectionUtils.constructDefault(validatorClass));
    }
}
