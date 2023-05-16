package com.globallogic.challenge.globallogicchallenge.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class TokenInfo implements Serializable {
    private String jwtToken;

    public TokenInfo(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}