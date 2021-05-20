package com.terheyden.valid;

import static java.lang.String.format;

/**
 * ValidateResult class.
 */
public class ValidateResult {

    private final ValidateContext context;
    private final boolean isValid;
    private final String message;

    public ValidateResult(ValidateContext context, boolean isValid, String message) {
        this.context = context;
        this.isValid = isValid;
        this.message = message;
    }

    public static ValidateResult success(ValidateContext context) {
        return new ValidateResult(context, true, "");
    }

    public static ValidateResult fail(ValidateContext context, String message, Object... args) {
        return new ValidateResult(context, false, args.length == 0 ? message : format(message, args));
    }

    public ValidateContext context() {
        return context;
    }

    public boolean isValid() {
        return isValid;
    }

    public String message() {
        return message;
    }
}
