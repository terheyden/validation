package com.terheyden.valid;

/**
 * PositiveValidator class.
 */
public class PositiveValidator extends NumberValidator {

    public PositiveValidator() {
        super(num -> num.intValue() > 0, "Number must be greater than 0");
    }
}
