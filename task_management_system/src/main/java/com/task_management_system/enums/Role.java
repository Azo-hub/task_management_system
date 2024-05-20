package com.task_management_system.enums;

import static com.task_management_system.security.Authority.ADMIN_AUTHORITIES;
import static com.task_management_system.security.Authority.USER_AUTHORITIES;

public enum Role {
    ROLE_USER(USER_AUTHORITIES),
    ROLE_ADMIN(ADMIN_AUTHORITIES);

    private String[] authorities;

    Role(String... authorities) {
        this.authorities = authorities;
    }

    public String[] getAuthorities() {
        return authorities;
    }

}
