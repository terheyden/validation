package com.terheyden.valid;

/**
 * ValidateResult class.
 */
public class ValidateResult {

    private static final ValidateResult SUCCESS = new ValidateResult(true, "");

    private final boolean isValid;
    private final String message;

    public ValidateResult(boolean isValid, String message) {
        this.isValid = isValid;
        this.message = message;
    }

    public static ValidateResult success() {
        return SUCCESS;
    }

    public static ValidateResult fail(String message) {
        return new ValidateResult(false, message);
    }

    public boolean isValid() {
        return isValid;
    }

    public String message() {
        return message;
    }
}
