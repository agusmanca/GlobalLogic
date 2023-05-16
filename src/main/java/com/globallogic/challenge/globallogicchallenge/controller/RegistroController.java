package com.globallogic.challenge.globallogicchallenge.controller;

import com.globallogic.challenge.globallogicchallenge.exception.errorModels.BadRequestEx;
import com.globallogic.challenge.globallogicchallenge.exception.errorModels.NotFoundEx;
import com.globallogic.challenge.globallogicchallenge.model.AuthenticationReq;
import com.globallogic.challenge.globallogicchallenge.model.LoginResponse;
import com.globallogic.challenge.globallogicchallenge.service.UsuarioService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/registro")
public class RegistroController {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody AuthenticationReq req) throws NotFoundEx, BadRequestEx {
        logger.info("Login para obtencion de datos de usuario.");
        return new ResponseEntity<>(usuarioService.login(req), HttpStatus.OK);
    }

    @GetMapping("/getToken/{username}")
    public ResponseEntity<String> getToken(@NotBlank @PathVariable String username) throws NotFoundEx {
        logger.info("Obtener Token a partir de username.");
        return new ResponseEntity<>(usuarioService.getToken(username),HttpStatus.OK);
    }
}
