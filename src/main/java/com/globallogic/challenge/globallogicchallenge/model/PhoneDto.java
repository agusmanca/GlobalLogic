package com.globallogic.challenge.globallogicchallenge.model;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.io.Serializable;

@Data
public class PhoneDto implements Serializable {
    @Pattern(regexp = "^[0-9]", message = "Field Number: Only numbers are acepted.")
    private Long number;

    @Pattern(regexp = "^[0-9]", message = "Field citycode: Only numbers are acepted.")
    private Integer citycode;

    private String contrycode;
}
