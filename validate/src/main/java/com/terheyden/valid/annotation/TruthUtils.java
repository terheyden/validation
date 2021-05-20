package com.terheyden.valid.annotation;

import javax.annotation.Nullable;
import java.util.Collection;

import org.eclipse.collections.impl.list.immutable.ImmutableIterator;

/**
 * TruthUtils class.
 */
public final class TruthUtils {

    public static final float FLOAT_MARGIN = 0.000_001F;

    private TruthUtils() {
        // Private since this class shouldn't be instantiated.
    }

    /**
     * Evaluates any object or value as a true-ish boolean.
     * Uses truthiness similar to Perl, Python, and Groovy.
     */
    public static boolean isTrue(@Nullable Object obj) {

        if (obj == null) {
            return false;
        }

        if (obj instanceof Boolean bool) {
            return bool;
        }

        if (obj instanceof Collection<?> coll) {
            return isCollectionTrue(coll);
        }

        if (obj instanceof ImmutableIterator<?> iter) {
            return isImmutableTrue(iter);
        }

        if (obj instanceof Number num) {
            return isNumberTrue(num);
        }

        if (obj instanceof StringBuilder builder) {
            return isStringTrue(builder);
        }

        if (obj instanceof CharSequence str) {
            return isStringTrue(str);
        }

        // We don't have a specialized evaluator for this type.
        return isUnknownTrue(obj);
    }

    /**
     * Evaluates any object or value as a true-ish boolean.
     * Uses truthiness similar to Perl, Python, and Groovy.
     */
    public static boolean isFalse(@Nullable Object obj) {
        return !isTrue(obj);
    }

    /**
     * A String is true if it is non-null, is non-empty, and is not equal to "0".
     * These are all true: {@code "hi", "1", " " (empty space)}
     * <p></p>
     * These are false: {@code null, "0", "" (empty string)}
     */
    public static boolean isStringBuilderTrue(@Nullable StringBuilder stringBuilder) {
        return stringBuilder != null && !stringBuilder.isEmpty();
    }

    public static boolean isStringBuilderFalse(@Nullable StringBuilder stringBuilder) {
        return !isStringBuilderTrue(stringBuilder);
    }

    /**
     * A String is true if it is non-null, is non-empty, and is not equal to "0".
     * These are all true: {@code "hi", "1", " " (empty space)}
     * <p></p>
     * These are false: {@code null, "0", "" (empty string)}
     */
    public static boolean isStringTrue(@Nullable CharSequence str) {
        return str != null && !"".contentEquals(str) && !"0".contentEquals(str);
    }

    public static boolean isStringFalse(@Nullable CharSequence str) {
        return !isStringTrue(str);
    }

    /**
     * A number is false if it equals 0.
     * For all non-zero values, it is true.
     */
    public static boolean isNumberTrue(@Nullable Number num) {
        if (num == null) { return false; }
        return Math.abs(num.floatValue()) > FLOAT_MARGIN;
    }

    public static boolean isNumberFalse(@Nullable Number num) {
        return !isNumberTrue(num);
    }

    /**
     * A collection is true if it is non-null and it is non-empty.
     */
    public static boolean isCollectionTrue(@Nullable Collection<?> coll) {
        return coll != null && !coll.isEmpty();
    }

    public static boolean isCollectionFalse(@Nullable Collection<?> coll) {
        return !isCollectionTrue(coll);
    }

    /**
     * An immutable collection is true if it is non-null and it is non-empty.
     */
    public static boolean isImmutableTrue(@Nullable ImmutableIterator<?> iter) {
        return iter != null && iter.hasNext();
    }

    public static boolean isImmutableFalse(@Nullable ImmutableIterator<?> iter) {
        return !isImmutableTrue(iter);
    }

    /**
     * For all other objects, they are true if they are non-null.
     */
    public static boolean isUnknownTrue(@Nullable Object obj) {
        return obj != null;
    }

    public static boolean isUnknownFalse(@Nullable Object obj) {
        return !isUnknownTrue(obj);
    }
}
