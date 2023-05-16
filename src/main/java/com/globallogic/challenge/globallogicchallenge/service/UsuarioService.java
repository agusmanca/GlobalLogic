package com.globallogic.challenge.globallogicchallenge.service;

import com.globallogic.challenge.globallogicchallenge.exception.errorModels.BadRequestEx;
import com.globallogic.challenge.globallogicchallenge.exception.errorModels.InternalServerErrorEx;
import com.globallogic.challenge.globallogicchallenge.exception.errorModels.NotFoundEx;
import com.globallogic.challenge.globallogicchallenge.model.AuthenticationReq;
import com.globallogic.challenge.globallogicchallenge.model.LoginResponse;
import com.globallogic.challenge.globallogicchallenge.model.UserCreatedResponse;
import com.globallogic.challenge.globallogicchallenge.model.UserDto;

public interface UsuarioService {
    UserCreatedResponse createUser(UserDto newUserDto) throws BadRequestEx, InternalServerErrorEx;
    LoginResponse login(AuthenticationReq req) throws BadRequestEx, NotFoundEx;
    String getToken(String username) throws NotFoundEx;
}
