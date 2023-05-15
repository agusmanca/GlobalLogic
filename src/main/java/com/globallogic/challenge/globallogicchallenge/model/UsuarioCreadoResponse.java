package com.globallogic.challenge.globallogicchallenge.model;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class UsuarioCreadoResponse implements Serializable {
    private UUID id;
    private UsuarioDto usuario;
    private LocalDateTime created;
    private LocalDateTime lastLogin;
    private String token;
    private Boolean isActive;
}
