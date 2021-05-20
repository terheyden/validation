package com.terheyden.valid;

import java.util.UUID;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * Example usage for validating entire objects.
 */
public class ThirdUser {

    @NotNull
    private UUID id;
    @NotEmpty
    private String name;
    @Positive
    private int age;
    @Email
    private String email;

    public ThirdUser(UUID id, String name, int age, String email) {
        this.id   = id;
        this.name = name;
        this.age  = age;
        this.email = email;
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public UUID id() {
        return id;
    }

    public ThirdUser id(UUID id) {
        this.id = id;
        return this;
    }

    public String name() {
        return name;
    }

    public ThirdUser name(String name) {
        this.name = name;
        return this;
    }

    public int age() {
        return age;
    }

    public ThirdUser age(int age) {
        this.age = age;
        return this;
    }

    public String email() {
        return email;
    }

    public ThirdUser email(String email) {
        this.email = email;
        return this;
    }

    /**
     * User builder class.
     */
    public static final class UserBuilder {

        @NotNull
        private UUID id;
        @NotEmpty
        private String name;
        @Positive
        private int age;
        @Email
        private String email;

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

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public ThirdUser build() {
            Validate.validateObj(this);
            return new ThirdUser(id, name, age, email);
        }
    }
}
