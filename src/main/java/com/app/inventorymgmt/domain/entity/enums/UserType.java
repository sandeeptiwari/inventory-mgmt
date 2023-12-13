package com.app.inventorymgmt.domain.entity.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum UserType {

    ADMIN("Admin"),
    USER("User");

    private String userType;

    UserType(String userType) {
        this.userType = userType;
    }

    @JsonValue
    public String getUserType() {
        return userType;
    }
}
