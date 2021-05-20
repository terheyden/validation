package com.terheyden.valid;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Validate unit tests.
 */
public class ValidateTest {

    private static final String EMPTY = "";
    private static final UUID ID = UUID.randomUUID();
    private static final String NAME = "Cora";

    @Test
    public void testObj() {

        User good = new User(ID, NAME, 10);
        Validate.validateObj(good);

        User badName = new User(ID, EMPTY, 10);
        assertThrows(ValidationException.class, () -> Validate.validateObj(badName));

        User badId = new User(null, NAME, 10);
        assertThrows(ValidationException.class, () -> Validate.validateObj(badId));

        User badAge = new User(ID, NAME, 0);
        assertThrows(ValidationException.class, () -> Validate.validateObj(badAge));
    }

    @Test
    public void testStatic() {

        MyStatic.staticMethod1(NAME, List.of("a", "b"));
        assertThrows(ValidationException.class, () -> MyStatic.staticMethod1(EMPTY, List.of()));
        assertThrows(ValidationException.class, () -> MyStatic.staticMethod1(NAME, List.of()));
    }
}
