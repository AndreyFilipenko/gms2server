package com.facom.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserSecurityTokenDto {
    private final String securityToken;

    @JsonCreator
    public UserSecurityTokenDto(@JsonProperty("securityToken") String securityToken) {
        this.securityToken = securityToken;
    }

    public String getSecurityToken() {
        return securityToken;
    }

    @Override
    public String toString() {
        return "UserSecurityTokenDto{" +
                "securityToken='" + securityToken + '\'' +
                '}';
    }
}
