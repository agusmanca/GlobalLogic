package com.globallogic.challenge.globallogicchallenge.service;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;


public interface JwtUtilService {
    String extractUsername(String token);
    Boolean isTokenExpired(String token);
    Date extractExpiration(String token);
    String generateToken(UserDetails userDetails);
    boolean validateToken(String token, UserDetails userDetails);
}
