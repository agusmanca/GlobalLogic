package com.globallogic.challenge.globallogicchallenge.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ErrorMessage {
    private List<String> detail;
    private Integer codigo;
    private LocalDateTime timestamp;

    public ErrorMessage(List<String> errors, HttpStatus status) {
        this.detail = errors;
        this.codigo = status.value();
        this.timestamp = LocalDateTime.now();
    }
}
