package com.terheyden.valid.annotation;

import com.terheyden.valid.ValidateContext;
import com.terheyden.valid.ValidateResult;

public interface Validator {

    ValidateResult validate(ValidateContext context);
}
