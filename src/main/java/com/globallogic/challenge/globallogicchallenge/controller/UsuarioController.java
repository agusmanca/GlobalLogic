package com.globallogic.challenge.globallogicchallenge.controller;

import com.globallogic.challenge.globallogicchallenge.exception.errorModels.BadRequestEx;
import com.globallogic.challenge.globallogicchallenge.exception.errorModels.InternalServerErrorEx;
import com.globallogic.challenge.globallogicchallenge.model.UserCreatedResponse;
import com.globallogic.challenge.globallogicchallenge.model.UserDto;
import com.globallogic.challenge.globallogicchallenge.service.UsuarioService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private UsuarioService service;

    @PostMapping("/sign-up")
    public ResponseEntity<UserCreatedResponse> createUser(@Valid @RequestBody UserDto newUser) throws BadRequestEx, InternalServerErrorEx {
        logger.info("Creacion de nuevo Usuario.");
        UserCreatedResponse response = service.createUser(newUser);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
