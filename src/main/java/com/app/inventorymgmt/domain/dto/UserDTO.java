package com.app.inventorymgmt.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String password;

    private boolean enabled;

    private String emailId;

    private String phone;

    private String userType;

    private String name;

}
