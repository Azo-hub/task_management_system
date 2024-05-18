package com.task_management_system.security;

public class Authority {
    public static final String[] USER_AUTHORITIES = { "user:create", "user:read", "user:update" };
    public static final String[] ADMIN_AUTHORITIES = { "user:create", "user:read", "user:update", "user:delete" };
}
