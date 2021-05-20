package com.terheyden.valid;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Optional;

import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.impl.factory.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.terheyden.valid.annotation.Validator;

import static java.lang.String.format;

/**
 * Validators class.
 */
public final class Validate {

    private static final Logger LOG = LoggerFactory.getLogger(Validate.class);

    private Validate() {
        // Private since this class shouldn't be instantiated.
    }

    /**
     * Perform Jakarta Bean Validation on a Validation-aware object.
     * @return the validated object, unchanged (for chaining)
     */
    public static <T> T validateObj(T validObj) {
        try {

            // Class.getFields() returns only public.
            Class<?> objClass = validObj.getClass();
            Field[] fields = objClass.getDeclaredFields();

            for (Field field : fields) {

                Object fieldVal = ReflectionUtils.getFieldValue(validObj, field);
                String label = format("%s.%s", objClass.getSimpleName(), field.getName());

                for (Annotation annotation : field.getAnnotations()) {
                    validateAnnotation(annotation, fieldVal, label);
                }
            }

            return validObj;

        } catch (Exception e) {
            return ReflectionUtils.throwUnchecked(e);
        }
    }

    public static void validateArgs(Class<?> methodClass, Object... args) {

        Method callingMethod = ReflectionUtils.findCallingMethod(methodClass, args);
        ImmutableList<Parameter> argObjs = Lists.immutable.of(callingMethod.getParameters());
        assert args.length == argObjs.size();

        for (int off = 0; off < args.length; off++) {

            Parameter argObj = argObjs.get(off);
            Object argVal = args[off];
            ImmutableList<Annotation> annotations = Lists.immutable.of(argObj.getAnnotations());
            String argLabel = format("%s() arg[%d]", callingMethod.getName(), off);

            // Verify each annotation on the method arg.
            annotations.each(ann -> validateAnnotation(ann, argVal, argLabel));
        }
    }

    /**
     * Validate the annotation (if it has a @{@link ValidatedBy} entry)
     * with the given field / arg value.
     */
    private static void validateAnnotation(Annotation annotation, Object value, String objLabel) {

        LOG.info("Analyzing {}: {}", objLabel, annotation);
        ValidateContext context = new ValidateContext(annotation, value);

        isValidatableAnnotation(annotation)
            .map(validator -> validator.validate(context))
            .ifPresent(result -> handleValidationResult(result, objLabel));
    }

    private static void handleValidationResult(ValidateResult result, String objLabel) {

        if (result.isValid()) {
            return;
        }

        throw new ValidationException(format("%s: %s", objLabel, result.message()));
    }

    /**
     * Returns the related {@link Validator} if the given annotation
     * is validatable.
     */
    private static Optional<Validator> isValidatableAnnotation(Annotation annotation) {
        return hasValidatedByAnnotation(annotation)
            .or(() -> has3rdPartyValidator(annotation));
    }

    /**
     * Returns the related {@link ValidatedBy}'s validator from the {@link ValidatorRegistry}
     * if the given annotation is validate-related.
     */
    private static Optional<Validator> hasValidatedByAnnotation(Annotation annotation) {
        return Lists.immutable
            .of(annotation.annotationType().getAnnotations())
            .detectOptional(ValidatedBy.class::isInstance)
            .map(ValidatedBy.class::cast)
            .map(ValidatorRegistry::getOrCreateValidator);
    }

    /**
     * For compatibility with other validation libs, we keep a registry of validators
     * for other annotations. This returns the associated validator if one exists
     * for the given annotation.
     */
    private static Optional<Validator> has3rdPartyValidator(Annotation annotation) {
        return ValidatorRegistry.find3rdPartyValidator(annotation.annotationType());
    }
}
