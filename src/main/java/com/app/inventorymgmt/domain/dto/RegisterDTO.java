package com.app.inventorymgmt.domain.dto;

import lombok.Data;

@Data
public class RegisterDTO {
    private String password;

    private String emailId;

    private String phone;

    private String userType;

    private String name;
}
