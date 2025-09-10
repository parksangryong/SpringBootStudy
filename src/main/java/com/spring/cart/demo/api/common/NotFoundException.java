package com.spring.cart.demo.api.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NotFoundException extends ResponseStatusException {
    public NotFoundException(String resource, Object key) {
        super(HttpStatus.NOT_FOUND, resource + " not found: " + key);
    }
}