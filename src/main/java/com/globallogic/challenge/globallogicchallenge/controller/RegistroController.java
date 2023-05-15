package com.globallogic.challenge.globallogicchallenge.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class RegistroController {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @GetMapping("/login:")
    public void login() {

    }
}
