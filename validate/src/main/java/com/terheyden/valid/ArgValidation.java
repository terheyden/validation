package com.terheyden.valid;

import javax.annotation.Nullable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import static java.util.Objects.requireNonNull;

/**
 * ArgValidation class.
 */
public class ArgValidation {

    private final Class<?> methodClass;
    private final Method methodObj;
    private final Parameter argObj;
    private final Object argVal;
    private final Annotation annotation;

    ArgValidation(
        Class<?> methodClass,
        Method methodObj,
        Parameter argObj,
        Object argVal,
        Annotation annotation) {

        this.methodClass   = methodClass;
        this.methodObj     = methodObj;
        this.argObj        = argObj;
        this.argVal        = argVal;
        this.annotation = annotation;
    }

    /**
     * Copy constructor.
     */
    public ArgValidation(ArgValidation argToCopy) {
        this(
            argToCopy.methodClass(),
            argToCopy.methodObj(),
            argToCopy.argObj(),
            argToCopy.argVal(),
            argToCopy.annotation());
    }

    public static ArgValidationBuilder builder() { return new ArgValidationBuilder(); }

    public ArgValidationBuilder copy() { return new ArgValidationBuilder(this); }

    public Class<?> methodClass() {
        return methodClass;
    }

    public Method methodObj() {
        return methodObj;
    }

    public Parameter argObj() {
        return argObj;
    }

    public Object argVal() {
        return argVal;
    }

    public Annotation annotation() {
        return annotation;
    }

    /**
     * For building.
     */
    public static final class ArgValidationBuilder {

        @Nullable
        private Class<?> methodClass;
        @Nullable
        private Method methodObj;
        @Nullable
        private Parameter argObj;
        @Nullable
        private Object argVal;
        @Nullable
        private Annotation annotation;

        ArgValidationBuilder() {
        }

        ArgValidationBuilder(ArgValidation argToCopy) {

            this.methodClass = argToCopy.methodClass();
            this.methodObj   = argToCopy.methodObj();
            this.argObj      = argToCopy.argObj();
            this.argVal      = argToCopy.argVal();
            this.annotation  = argToCopy.annotation();
        }

        public ArgValidationBuilder methodClass(Class<?> methodClass) {
            this.methodClass = methodClass;
            return this;
        }

        public ArgValidationBuilder methodObj(Method methodObj) {
            this.methodObj = methodObj;
            return this;
        }

        public ArgValidationBuilder argObj(Parameter argObj) {
            this.argObj = argObj;
            return this;
        }

        public ArgValidationBuilder argVal(Object argVal) {
            this.argVal = argVal;
            return this;
        }

        public ArgValidationBuilder topAnnotation(Annotation topAnnotation) {
            this.annotation = topAnnotation;
            return this;
        }

        public ArgValidation build() {
            return new ArgValidation(
                requireNonNull(methodClass),
                requireNonNull(methodObj),
                requireNonNull(argObj),
                requireNonNull(argVal),
                requireNonNull(annotation));
        }
    }
}
