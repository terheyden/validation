package com.terheyden.valid;

/**
 * Address class.
 */
public class Address {

    private final String street;
    private final String city;

    public Address(String street, String city) {
        this.street = street;
        this.city   = city;
    }

    @NotEmpty
    public String street() {
        return street;
    }

    @NotEmpty
    public String city() {
        return city;
    }
}
