package com.globallogic.challenge.globallogicchallenge.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserDto implements Serializable {

    private String name;

    @NotBlank(message = "Field Email: The email is required.")
    @Email(message = "Field Email: Invalid email format.")
    private String email;

    @NotBlank(message = "Field Password: The password is required.")
    //@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,12}$", message = "Field Password: Invalid password format")
    private String password;

    private RoleEnum role;

    private List<PhoneDto> phones;
}
