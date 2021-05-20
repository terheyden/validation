package com.terheyden.valid;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.terheyden.valid.annotation.Max;
import com.terheyden.valid.annotation.Min;
import com.terheyden.valid.annotation.NotEmpty;
import com.terheyden.valid.annotation.NotNull;
import com.terheyden.valid.annotation.Pattern;
import com.terheyden.valid.annotation.Positive;
import com.terheyden.valid.annotation.Size;

/**
 * Example usage for validating entire objects.
 */
public class User {

    @NotNull
    private UUID id;
    @NotEmpty
    private String name;
    @Positive @Min(3) @Max(100) @Size(min = 3, max = 100)
    private int age;
    @NotNull
    @Pattern(".+@.+")
    private String email;
    @Size(1) @Min(1) @Pattern("\\d+ .+")
    private final List<String> addresses = Arrays.asList("123 hi st");

    public User(UUID id, String name, int age, String email) {
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

    public User id(UUID id) {
        this.id = id;
        return this;
    }

    public String name() {
        return name;
    }

    public User name(String name) {
        this.name = name;
        return this;
    }

    public int age() {
        return age;
    }

    public User age(int age) {
        this.age = age;
        return this;
    }

    public String email() {
        return email;
    }

    public User email(String email) {
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
        @Pattern(".+@.+")
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

        public User build() {
            Validate.validateObj(this);
            return new User(id, name, age, email);
        }
    }
}
