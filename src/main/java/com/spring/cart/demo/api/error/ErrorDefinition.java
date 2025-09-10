package com.spring.cart.demo.api.error;

import org.springframework.http.HttpStatus;

public record ErrorDefinition(String code, HttpStatus status, String message) {}
