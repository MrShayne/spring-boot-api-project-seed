package com.company.project.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    // todo: add your own role here
    ROLE_TRAVELER, ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
