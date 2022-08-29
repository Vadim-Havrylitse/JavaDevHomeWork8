package com.example.JavaDevHomeWork8.user.entity;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
@Getter
public enum Role implements GrantedAuthority {
    USER("USER"),
    ADMIN("ADMIN");

    private String authority;

    Role(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
            return this.authority;
    }
}