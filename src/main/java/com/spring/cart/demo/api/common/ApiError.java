package com.spring.cart.demo.api.common;

import org.springframework.http.HttpStatus;
import java.time.Instant;

public class ApiError {
    private final Instant timestamp = Instant.now();
    private final int status;
    private final String error;
    private final String code;
    private final String message;
    private final String path;

    private ApiError(HttpStatus status, String message, String code, String path) {
        this.status = status.value();
        this.error = status.getReasonPhrase();
        this.code = code;
        this.message = message;
        this.path = path;
    }
    public static ApiError of(HttpStatus s, String msg, String code, String path) {
        return new ApiError(s, msg, code, path);
    }

    // getters ...
}
