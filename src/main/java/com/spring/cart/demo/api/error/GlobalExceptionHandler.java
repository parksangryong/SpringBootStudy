package com.spring.cart.demo.api.error;

import com.spring.cart.demo.api.dto.ErrorResponse;
import com.spring.cart.demo.api.common.AppException;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<ErrorResponse> respond(HttpStatus status, String code, String message) {
        return ResponseEntity.status(status)
                .body(new ErrorResponse(message, status.value(), code));
    }

    // 도메인 예외: 우리가 정의한 코드 그대로
    @ExceptionHandler(AppException.class)
    public ResponseEntity<ErrorResponse> handleApp(AppException ex) {
        var e = ex.error();
        return respond(e.status(), e.code(), e.message());
    }

    // 스프링 표준 상태 예외
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse> handleRse(ResponseStatusException ex) {
        HttpStatus status = (ex.getStatusCode() instanceof HttpStatus s) ? s : HttpStatus.INTERNAL_SERVER_ERROR;
        String code = status.name();
        String msg  = (ex.getReason() != null) ? ex.getReason() : status.getReasonPhrase();
        return respond(status, code, msg);
    }

    // 400: 바디 파싱 오류
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleNotReadable(HttpMessageNotReadableException ex) {
        return respond(HttpStatus.BAD_REQUEST, "BAD_REQUEST", "Malformed request");
    }

    // 400: @Valid 바디 검증
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        String msg = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .collect(Collectors.joining(", "));
        if (msg.isBlank()) msg = "Validation failed";
        return respond(HttpStatus.BAD_REQUEST, "VALIDATION_ERROR", msg);
    }

    // 404: 잘못된 URL
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoHandler(NoHandlerFoundException ex) {
        String msg = "No handler for %s %s".formatted(ex.getHttpMethod(), ex.getRequestURL());
        return respond(HttpStatus.NOT_FOUND, "NOT_FOUND", msg);
    }

    // 나머지 전부 500 통일
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex) {
        return respond(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_ERROR", "Internal server error");
    }
}
