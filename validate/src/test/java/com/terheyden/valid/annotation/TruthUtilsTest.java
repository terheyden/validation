package com.terheyden.valid.annotation;

import java.util.List;

import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.impl.factory.Lists;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * TruthUtilsTest unit tests.
 */
public class TruthUtilsTest {

    private static final Logger LOG = getLogger(TruthUtilsTest.class);

    private static final ImmutableList<Object> TRUE = Lists.immutable.of(
        true, "1", " ", "hi", 1, -0.2F, List.of(0), new StringBuilder(" "));

    private static final ImmutableList<Object> FALSE = Lists.immutable.of(
        false, null, "", "0", 0, -0.0D, -0.00000001D, List.of(), new StringBuilder());

    @Test
    public void testTrue() {

        for (Object trueObj : TRUE) {
            LOG.debug("Testing for truth: {}", trueObj);
            assertTrue(TruthUtils.isTrue(trueObj));
        }
    }

    @Test
    public void testFalse() {

        for (Object falseObj : FALSE) {
            LOG.debug("Testing for falsehood: {}", falseObj);
            assertTrue(TruthUtils.isFalse(falseObj));
        }
    }
}
