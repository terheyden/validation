package com.terheyden.valid.annotation;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Map;

import org.eclipse.collections.api.collection.ImmutableCollection;

/**
 * SizeChecker class for validating things with sizes - strings, collections, maps, etc.
 * TODO: Can be cached.
 */
public class SizeChecker {

    private final long minVal;
    private final long maxVal;

    public SizeChecker(long minVal, long maxVal) {
        this.minVal = minVal;
        this.maxVal = maxVal;
    }

    public boolean isValidSize(@Nullable Object obj) {

        if (obj == null) {
            return minVal == 0 && maxVal == 0;
        }

        if (obj instanceof Collection<?> coll) {
            return isValid(coll);
        }

        if (obj instanceof ImmutableCollection<?> imm) {
            return isValid(imm.castToCollection());
        }

        if (obj instanceof Map<?, ?> map) {
            return isValid(map);
        }

        if (obj instanceof Number num) {
            return isValid(num);
        }

        if (obj instanceof Object[] arr) {
            return isValid(arr);
        }

        if (obj instanceof CharSequence str) {
            return isValid(str);
        }

        // We don't know what it is.
        // This annotation could have been applied at the class level.
        // For unknown objs, assume true.
        return true;
    }

    private boolean isValid(Number num) {

        float val = num.floatValue();
        return val >= minVal && val <= maxVal;
    }

    private boolean isValid(CharSequence str) {
        return str.length() >= minVal && str.length() <= maxVal;
    }

    private boolean isValid(Collection<?> coll) {
        return coll.size() >= minVal && coll.size() <= maxVal;
    }

    private boolean isValid(Object[] arr) {
        return arr.length >= minVal && arr.length <= maxVal;
    }

    private boolean isValid(Map<?, ?> map) {
        return map.size() >= minVal && map.size() <= maxVal;
    }
}
