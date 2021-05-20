package com.terheyden.valid;

import java.util.function.Predicate;

/**
 * NumberValidator class.
 */
public class NumberValidator implements Validator {

    private final Predicate<Number> isValidNum;
    private final String failMsg;

    public NumberValidator(Predicate<Number> isValidNum, String failMsg) {
        this.isValidNum = isValidNum;
        this.failMsg    = failMsg;
    }

    @Override
    public ValidateResult validate(Object val) {

        if (!(val instanceof Number num)) {
            return ValidateResult.fail("Not a number type");
        }

        return isValidNum.test(num) ? ValidateResult.success() : ValidateResult.fail(failMsg);
    }
}
