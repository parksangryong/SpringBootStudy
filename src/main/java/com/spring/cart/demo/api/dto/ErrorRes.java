package com.spring.cart.demo.api.dto;

public record ErrorRes(
        String message, // 사람이 읽을 메시지
        int status,     // HTTP 상태코드 숫자
        String code     // 에러 코드(상태명 또는 도메인 코드)
) {}
