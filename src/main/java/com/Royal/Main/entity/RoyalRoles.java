package com.Royal.Main.entity;

public enum RoyalRoles {
    SELLER("ROLE_SELLER"),
    CUSTOMER("ROLE_CUSTOMER"),
    ADMIN("ROLE_ADMIN");

    private String role;

    RoyalRoles(String role) {
        this.role = role;
    }
}
