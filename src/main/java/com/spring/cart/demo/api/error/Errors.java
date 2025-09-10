package com.spring.cart.demo.api.error;

import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public final class Errors {
    private Errors() {}

    // ===== 그룹별 상수 =====
    public static final class JWT {
        public static final ErrorDefinition ACCESS_EXPIRED =
                new ErrorDefinition("JWT-002", HttpStatus.UNAUTHORIZED, "액세스 토큰이 만료되었습니다");
        public static final ErrorDefinition REFRESH_EXPIRED =
                new ErrorDefinition("JWT-001", HttpStatus.UNAUTHORIZED, "리프레시 토큰이 만료되었습니다");
        public static final ErrorDefinition TOKEN_REQUIRED =
                new ErrorDefinition("JWT-003", HttpStatus.UNAUTHORIZED, "로그인이 필요한 서비스입니다");
        public static final ErrorDefinition INVALID_REFRESH_TOKEN =
                new ErrorDefinition("JWT-004", HttpStatus.UNAUTHORIZED, "로그인 세션이 만료되었습니다. 다시 로그인해주세요");
        public static final ErrorDefinition REFRESH_TOKEN_REQUIRED =
                new ErrorDefinition("JWT-005", HttpStatus.UNAUTHORIZED, "로그인 세션이 만료되었습니다. 다시 로그인해주세요");
        public static final ErrorDefinition INVALID_ACCESS_TOKEN =
                new ErrorDefinition("JWT-006", HttpStatus.UNAUTHORIZED, "로그인 세션이 만료되었습니다. 다시 로그인해주세요");
        public static final ErrorDefinition DB_ERROR =
                new ErrorDefinition("JWT-007", HttpStatus.UNAUTHORIZED, "로그인 처리 중 오류가 발생했습니다. 다시 시도해주세요");
        public static final ErrorDefinition NO_DATA =
                new ErrorDefinition("JWT-008", HttpStatus.NOT_FOUND, "데이터가 존재하지 않습니다");
    }

    public static final class USER {
        public static final ErrorDefinition USER_NOT_FOUND =
                new ErrorDefinition("USER-001", HttpStatus.NOT_FOUND, "존재하지 않는 유저입니다");
    }

    // ===== 코드 → 정의 역조회 레지스트리 =====
    private static final Map<String, ErrorDefinition> REGISTRY = new HashMap<>();
    static {
        // JWT
        register(JWT.ACCESS_EXPIRED);
        register(JWT.REFRESH_EXPIRED);
        register(JWT.TOKEN_REQUIRED);
        register(JWT.INVALID_REFRESH_TOKEN);
        register(JWT.REFRESH_TOKEN_REQUIRED);
        register(JWT.INVALID_ACCESS_TOKEN);
        register(JWT.DB_ERROR);
        register(JWT.NO_DATA);
        // USER
        register(USER.USER_NOT_FOUND);
    }
    private static void register(ErrorDefinition def) { REGISTRY.put(def.code(), def); }
    public static ErrorDefinition byCode(String code) {
        ErrorDefinition def = REGISTRY.get(code);
        if (def == null) throw new IllegalArgumentException("Unknown error code: " + code);
        return def;
    }
}
