package com.terheyden.valid;

import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolationException;

/**
 * ValidatorsTest unit tests.
 */
public class ValidatorsTest {

    private static final String NAME = "Cora";
    private static final int AGE = 10;

    @Test
    public void validTest() {

        // The build() method validates for us.
        // Test bean validation.
        User.builder()
            .id(UUID.randomUUID())
            .name(NAME)
            .age(AGE)
            .build();

        // Test method arg validation.
        UserRepo repo = new UserRepo();
        repo.createUser(NAME, AGE);
    }

    @Test
    public void invalidTest() {

        // Test bean validation fails.
        Assertions.assertThrows(ConstraintViolationException.class, () -> User.builder().build());

        // Test method arg validation fail.
        UserRepo repo = new UserRepo();
        Assertions.assertThrows(ConstraintViolationException.class, () -> repo.createUser("", -1));
    }

    //@Test
    public void testStatic() {

        UserRepo.join("hi ", 2);
    }
}
