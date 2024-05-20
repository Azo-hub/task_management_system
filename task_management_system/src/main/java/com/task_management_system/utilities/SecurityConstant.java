package com.task_management_system.utilities;

public class SecurityConstant {
    public static final long JWT_EXPIRATION_TIME = 24 * 60 * 60 * 1000; // 24 hours
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String JWT_TOKEN_HEADER = "Jwt-Token";
    public static final String TOKEN_CANNOT_BE_VERIFIED = "Token cannot be verified";
    public static final String TASK_MANAGEMENT_SYSTEM = "Task Management System";
    public static final String TASK_MANAGEMENT_SYSTEM_ADMIN = "Admin Portal";
    public static final String AUTHORITIES = "authorities";
    public static final String FORBIDDEN_MESSAGE = "You need to log in to access this page";
    public static final String ACCESS_DENIED_MESSAGE = "You do not have permission to access this page";
    public static final String OPTIONS_HTTP_METHOD = "OPTIONS";
    public static final int LOGIN_MAX_FAILED_ATTEMPTS = 4;
    public static final long LOGIN_LOCK_TIME_DURATION = 24 * 60 * 60 * 1000;

    public static final String[] PUBLIC_URLS = {
            "/login", "/error", "/signUp","/v2/api-docs", "/v3/api-docs", "/v3/api-docs/**",
            "/swagger-resources", "/swagger-resources/**", "/configuration/ui", "/configuration/security",
            "/swagger-ui/**", "/webjars/**", "/swagger-ui.html"

    };
}
