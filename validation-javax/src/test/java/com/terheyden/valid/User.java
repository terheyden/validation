package com.terheyden.valid;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.UUID;

/**
 * Example usage for validating entire objects.
 */
public class User {

    @NotNull
    private final UUID id;
    @NotNullOrEmpty
    private final String name;
    @Positive
    private final int age;

    public User(UUID id, String name, int age) {
        this.id   = id;
        this.name = name;
        this.age  = age;
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public UUID id() {
        return id;
    }

    public String name() {
        return name;
    }

    public int age() {
        return age;
    }

    /**
     * User builder class.
     */
    public static final class UserBuilder {

        @NotNull
        private UUID id;
        @NotNullOrEmpty
        private String name;
        @Positive
        private int age;

        public UserBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public UserBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder age(int age) {
            this.age = age;
            return this;
        }

        public User build() {
            Validators.validateObj(this);
            return new User(id, name, age);
        }
    }
}
