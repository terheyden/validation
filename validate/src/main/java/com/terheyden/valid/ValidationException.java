package com.terheyden.valid;

import java.io.Serial;

/**
 * ValidationException class.
 */
public class ValidationException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 5446247460010925588L;

    public ValidationException() {
    }

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidationException(Throwable cause) {
        super(cause);
    }

    public ValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
