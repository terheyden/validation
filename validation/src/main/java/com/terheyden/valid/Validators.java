package com.terheyden.valid;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;

import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.executable.ExecutableValidator;

import static java.lang.String.format;

/**
 * Validators class.
 */
public final class Validators {

    public static final ValidatorFactory FACTORY = Validation.buildDefaultValidatorFactory();
    public static final Validator VALIDATOR = FACTORY.getValidator();
    public static final ExecutableValidator EXEC_VALIDATOR = VALIDATOR.forExecutables();
    private static final Logger LOG = LoggerFactory.getLogger(Validators.class);

    private Validators() {
        // Private since this class shouldn't be instantiated.
    }

    /**
     * Perform Jakarta Bean Validation on a Validation-aware object.
     * @return the validated object, unchanged (for chaining)
     */
    public static <T> T validateObj(T validObj) {
        throwViolations(VALIDATOR.validate(validObj));
        return validObj;
    }

    /**
     * Perform Jakarta Bean Validation on a method's arguments.
     * You should call this from inside the method in place of guard checks.
     * <p></p>
     * Unfortunately, validating args of static methods is not supported.
     * <p></p>
     * Make sure you pass ALL the method arguments, not just the ones needing validation,
     * because we use the arg count to match the method's signature.
     * <p></p>
     * @see <a href="https://docs.jboss.org/hibernate/stable/validator/reference/en-US/html_single/#_parameter_constraints" target="_top">parameter_constraints</a>
     */
    public static void validateArgs(Object classObj, Object... allMethodArgs) {

        Method thisMethod = findCallingMethod(classObj.getClass(), allMethodArgs);
        // Finally, we have everything we need to perform method arg validation.
        throwViolations(EXEC_VALIDATOR.validateParameters(classObj, thisMethod, allMethodArgs));
    }

    private static Method findCallingMethod(Class<?> methodObjClass, Object... allMethodArgs) {
        // The caller will be right beneath us on the stack,
        // so we look up our frame number and add 1.
        // Make sure this string matches this method's name 👇🏽👇🏽👇🏽
        int thisStackFrameNum = findStackFrameByMethodName("findCallingMethod");
        int stackFrame = thisStackFrameNum + 2;
        int callingMethodArgCount = allMethodArgs.length;

        // We can figure out our caller's method name and instance by walking the stack.
        return getStackMethod(
            methodObjClass,
            stackFrame,
            callingMethodArgCount);
    }

    /**
     * Validating returns a set of violations.
     * This turns that set into an {@link ConstraintViolationException}.
     * Does nothing if the set is empty.
     */
    private static void throwViolations(Set<? extends ConstraintViolation<?>> violations) {

        if (violations.isEmpty()) { return; }

        String errorMsg = Sets.adapt(violations)
            .collect(Validators::constraintViolationToString)
            .makeString("; ");

        throw new ConstraintViolationException(errorMsg, violations);
    }

    /**
     * When a validation fails, we're given some {@link ConstraintViolation} instances.
     * Convert them into useful strings we can show to the user.
     */
    private static <T> String constraintViolationToString(ConstraintViolation<T> violation) {
        return format("%s: %s %s",
            violation.getRootBeanClass().getSimpleName(),
            violation.getPropertyPath().toString(),
            violation.getMessage());
    }

    /**
     * For introspecting which stack frame you're interested in.
     * Dumps the first 5 and the last 5 stack frames to the log output.
     */
    private static void dumpStack() {

        StackTraceElement[] stack = Thread.currentThread().getStackTrace();

        for (int off = 0; off < stack.length; off++) {

            if (off > 9 && off < stack.length - 10) { continue; }
            LOG.info("Stack [{}] = {}", off, stack[off]);
        }
    }

    /**
     * Returns the method N number of frames down the stack.
     * Use {@link #dumpStack()} to determine the right frame number.
     */
    private static Method getStackMethod(Class<?> containingClass, int frameNum, int methodArgNum) {

        StackTraceElement frame = Thread.currentThread().getStackTrace()[frameNum];
        String methodName = frame.getMethodName();
        LOG.debug("Finding stack method[{}]: {}({})", frameNum, methodName, methodArgNum);
        dumpClass(containingClass);

        return Lists.mutable
            .of(containingClass.getDeclaredMethods())
            .select(method -> method.getParameterCount() == methodArgNum)
            .detectOptional(method -> method.getName().equals(methodName))
            .orElseThrow(() -> new IllegalStateException(format(
                "Could not find stack method[%d]: %s(%d)", frameNum, methodName, methodArgNum)));
    }

    private static int findStackFrameByMethodName(String methodName) {

        int off = 0;

        for (StackTraceElement frame : Thread.currentThread().getStackTrace()) {
            if (frame.getMethodName().equals(methodName)) {
                return off;
            }
            off++;
        }

        throw new IllegalArgumentException("Method not found: " + methodName);
    }

    private static void dumpClass(Class<?> classObj) {
        Arrays
            .stream(classObj.getDeclaredMethods())
            .map(method -> format("%s(%d)", method.getName(), method.getParameterCount()))
            .sorted()
            .forEach(methodStr -> LOG.info("Class method => {}", methodStr));
    }
}
