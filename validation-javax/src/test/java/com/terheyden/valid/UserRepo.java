package com.terheyden.valid;

import javax.validation.constraints.Positive;
import java.util.UUID;

/**
 * UserRepo class.
 */
public class UserRepo {

    /**
     * Example of how to validate method args.
     * Note that this can't be done for static methods at the moment.
     */
    public User createUser(@NotNullOrEmpty String name, @Positive int age) {

        Validators.validateArgs(this, name, age);
        return new User(UUID.randomUUID(), name, age);
    }
}
