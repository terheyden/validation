package com.terheyden.valid;

import javax.annotation.Nullable;
import java.lang.reflect.Array;
import java.util.Collection;

import org.eclipse.collections.api.PrimitiveIterable;

/**
 * ValidUtils class.
 */
public final class ValidUtils {

    private ValidUtils() {
        // Private since this class shouldn't be instantiated.
    }

    public static boolean isMap(@Nullable Object obj) {
        return obj instanceof Collection<?> || obj instanceof PrimitiveIterable;
    }

    public static boolean isCollection(@Nullable Object obj) {
        return obj instanceof Collection<?> || obj instanceof PrimitiveIterable;
    }

    /**
     * Determine if the object is an array. Returns false if given a null value.
     * https://stackoverflow.com/questions/2725533/how-to-see-if-an-object-is-an-array-without-using-reflection
     */
    public static boolean isArray(@Nullable Object arrayObj) {

        if (arrayObj == null) {
            return false;
        }

        return arrayObj.getClass().isArray();
    }

    /**
     * Return the size of an unknown array obj.
     * It's assumed that you already confirmed this with {@link #isArray(Object)}.
     */
    public static int getArraySize(@Nullable Object arrayObj) {
        if (arrayObj == null) { return 0; }
        return Array.getLength(arrayObj);
    }
}
