package com.spring.cart.demo.api.model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record Item(
        @NotBlank @Size(max=64) String sku,
        @NotBlank @Size(max=100) String name,
        @Positive int quantity,
        @PositiveOrZero BigDecimal unitPrice,
        @Size(max=200) String note
        ){}
