package com.terheyden.valid;

import java.util.List;

import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.primitive.MutableIntList;
import org.eclipse.collections.api.set.ImmutableSet;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.Sets;
import org.eclipse.collections.impl.factory.primitive.IntLists;
import org.eclipse.collections.impl.stack.mutable.primitive.IntArrayStack;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * ValidUtilsTest unit tests.
 */
public class ValidUtilsTest {

    private static final int[] PRIM_INT_ARRAY = { 1, 2, 3 };
    private static final int[] PRIM_INT_EMPTY = { };
    private static final Long[] BOX_LONG_ARRAY = { 1L, 2L, 3L };
    private static final Long[] BOX_LONG_EMPTY = { };
    private static final String RED = "red";
    private static final String GREEN = "green";
    private static final String[] STR_ARRAY = { RED, GREEN };
    private static final String[] STR_EMPTY = new String[0];

    private static final List<String> STR_LIST = List.of(RED, GREEN);
    private static final MutableSet<String> STR_MUT_SET = Sets.mutable.of(RED, GREEN);
    private static final ImmutableSet<String> STR_IMM_SET = Sets.immutable.of(RED, GREEN);
    private static final ImmutableList<String> STR_IMM_LIST = Lists.immutable.of(RED, GREEN);
    private static final MutableIntList PRIM_INT_LIST = IntLists.mutable.of(1, 2, 3);
    private static final IntArrayStack PRIM_STACK = IntArrayStack.newStackWith(1, 2, 3);

    @Test
    public void testArray() {

        assertFalse(ValidUtils.isArray(null));
        assertFalse(ValidUtils.isArray("string"));
        assertFalse(ValidUtils.isArray(List.of(1, 2)));

        assertTrue(ValidUtils.isArray(PRIM_INT_ARRAY));
        assertTrue(ValidUtils.isArray(PRIM_INT_EMPTY));
        assertTrue(ValidUtils.isArray(BOX_LONG_ARRAY));
        assertTrue(ValidUtils.isArray(BOX_LONG_EMPTY));
        assertTrue(ValidUtils.isArray(STR_ARRAY));
        assertTrue(ValidUtils.isArray(STR_EMPTY));

        assertEquals(3, ValidUtils.getArraySize(PRIM_INT_ARRAY));
        assertEquals(0, ValidUtils.getArraySize(PRIM_INT_EMPTY));
        assertEquals(3, ValidUtils.getArraySize(BOX_LONG_ARRAY));
        assertEquals(0, ValidUtils.getArraySize(BOX_LONG_EMPTY));
        assertEquals(2, ValidUtils.getArraySize(STR_ARRAY));
        assertEquals(0, ValidUtils.getArraySize(STR_EMPTY));
    }

    @Test
    public void testCollection() {

        assertTrue(ValidUtils.isCollection(STR_LIST));
        assertTrue(ValidUtils.isCollection(STR_MUT_SET));
        assertTrue(ValidUtils.isCollection(STR_IMM_SET));
        assertTrue(ValidUtils.isCollection(STR_IMM_LIST));
        assertTrue(ValidUtils.isCollection(PRIM_INT_LIST));
        assertTrue(ValidUtils.isCollection(PRIM_STACK));
    }
}
