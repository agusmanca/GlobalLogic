package com.globallogic.challenge.globallogicchallenge.model;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class UserCreatedResponse implements Serializable {
    private UUID id;
    private UserDto user;
    private LocalDateTime created;
    private LocalDateTime lastLogin;
    private String token;
    private Boolean isActive;
}
