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
    private static final String EMAIL = "cora@email.com";

    @Test
    public void testObj() {

        User good = new User(ID, NAME, 10, EMAIL);
        Validate.validateObj(good);

        User badName = new User(ID, EMPTY, 10, EMAIL);
        assertThrows(ValidationException.class, () -> Validate.validateObj(badName));

        User badId = new User(null, NAME, 10, EMAIL);
        assertThrows(ValidationException.class, () -> Validate.validateObj(badId));

        User badAge = new User(ID, NAME, 0, EMAIL);
        assertThrows(ValidationException.class, () -> Validate.validateObj(badAge));
    }

    @Test
    public void testPattern() {

        User good = new User(ID, NAME, 10, EMAIL);
        Validate.validateObj(good);

        User bad = new User(ID, NAME, 10, "bad email");
        assertThrows(ValidationException.class, () -> Validate.validateObj(bad));
    }

    @Test
    public void testStatic() {

        MyStatic.staticMethod1(NAME, List.of("a", "b"));
        assertThrows(ValidationException.class, () -> MyStatic.staticMethod1(EMPTY, List.of()));
        assertThrows(ValidationException.class, () -> MyStatic.staticMethod1(NAME, List.of()));
    }

    @Test
    public void test3rdParty() {

        ThirdUser good = new ThirdUser(ID, NAME, 10, EMAIL);
        Validate.validateObj(good);

        ThirdUser badName = new ThirdUser(ID, EMPTY, 10, EMAIL);
        assertThrows(ValidationException.class, () -> Validate.validateObj(badName));

        ThirdUser badId = new ThirdUser(null, NAME, 10, EMAIL);
        assertThrows(ValidationException.class, () -> Validate.validateObj(badId));

        ThirdUser badAge = new ThirdUser(ID, NAME, 0, EMAIL);
        assertThrows(ValidationException.class, () -> Validate.validateObj(badAge));
    }
}
