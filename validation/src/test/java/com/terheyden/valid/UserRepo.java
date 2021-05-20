package com.terheyden.valid;

import java.util.UUID;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

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

    public static String join(@NotEmpty Object part1, @NotEmpty Object part2) {

        Validators.validateArgs(UserRepo.class, part1, part2);
        return part1.toString() + part2.toString();
    }
}
