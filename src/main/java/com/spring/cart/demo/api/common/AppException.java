package com.spring.cart.demo.api.common;

import com.spring.cart.demo.api.error.ErrorDefinition;
import com.spring.cart.demo.api.error.Errors;

public class AppException extends RuntimeException {
    private final ErrorDefinition error;

    public AppException(ErrorDefinition error) {
        super(error.message());
        this.error = error;
    }
    public ErrorDefinition error() { return error; }

    public static AppException ofCode(String code) {
        return new AppException(Errors.byCode(code));
    }
}
