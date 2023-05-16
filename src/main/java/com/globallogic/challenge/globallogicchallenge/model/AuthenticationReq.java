package com.globallogic.challenge.globallogicchallenge.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

@Data
public class AuthenticationReq implements Serializable {

    @NotBlank(message = "Field user - User is required")
    private String user;
    @NotBlank(message = "Field password - Password is required")
    private String password;

    private RoleEnum role;

    public AuthenticationReq(String user, String password, RoleEnum role) {
        this.user = user;
        this.password = password;
        this.role = role;
    }
}
