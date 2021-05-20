package com.terheyden.valid;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.eclipse.collections.impl.factory.Lists;
import org.slf4j.Logger;

import static java.lang.String.format;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * ReflectionUtils class.
 */
public final class ReflectionUtils {

    private static final Logger LOG = getLogger(ReflectionUtils.class);

    private ReflectionUtils() {
        // Private since this class shouldn't be instantiated.
    }

    /**
     * Gets an object field's value via reflection.
     */
    static Object getFieldValue(Object validObj, Field field) {
        try {

            boolean canAccess = field.canAccess(validObj);
            if (!canAccess) { field.setAccessible(true); }
            Object fieldVal = field.get(validObj);
            if (!canAccess) { field.setAccessible(false); }
            return fieldVal;

        } catch (Exception e) {
            return throwUnchecked(e);
        }
    }

    static Method findCallingMethod(Class<?> methodObjClass, Object... allMethodArgs) {
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
     * For introspecting which stack frame you're interested in.
     * Dumps the first 5 and the last 5 stack frames to the log output.
     */
    private static void dumpStack() {

        StackTraceElement[] stack = Thread.currentThread().getStackTrace();

        for (int off = 0; off < stack.length; off++) {

            if (off > 4 && off < stack.length - 5) { continue; }
            LOG.info("Stack [{}] = {}", off, stack[off]);
        }
    }

    /**
     * Returns the method N number of frames down the stack.
     * Use {@link #dumpStack()} to determine the right frame number.
     */
    private static Method getStackMethod(Class<?> containingClass, int frameNum, int methodArgNum) {

        dumpClass(containingClass);
        StackTraceElement frame = Thread.currentThread().getStackTrace()[frameNum];
        String methodName = frame.getMethodName();
        LOG.debug("Finding stack method[{}]: {}({})", frameNum, methodName, methodArgNum);

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
            .forEach(method -> LOG.info("Class method => {}", method.getName()));
    }

    /**
     * Throw (rethrow) any checked exception as unchecked.
     */
    @SuppressWarnings("unchecked")
    public static <E extends Throwable, T> T throwUnchecked(Throwable exception) throws E {
        throw (E) exception;
    }

    public static <T> T constructDefault(Class<? extends T> classToCreate) {
        try {

            return classToCreate.getConstructor().newInstance();

        } catch (Exception e) {
            return throwUnchecked(e);
        }
    }
}
