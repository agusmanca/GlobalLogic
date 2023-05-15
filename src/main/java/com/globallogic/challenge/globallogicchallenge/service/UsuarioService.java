package com.globallogic.challenge.globallogicchallenge.service;

import com.globallogic.challenge.globallogicchallenge.exception.errorModels.BadRequestEx;
import com.globallogic.challenge.globallogicchallenge.exception.errorModels.InternalServerErrorEx;
import com.globallogic.challenge.globallogicchallenge.model.UsuarioCreadoResponse;
import com.globallogic.challenge.globallogicchallenge.model.UsuarioDto;

public interface UsuarioService {
    UsuarioCreadoResponse createUser(UsuarioDto newUserDto) throws BadRequestEx, InternalServerErrorEx;
}
