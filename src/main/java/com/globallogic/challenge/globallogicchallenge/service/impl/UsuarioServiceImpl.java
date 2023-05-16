package com.globallogic.challenge.globallogicchallenge.service.impl;

import com.globallogic.challenge.globallogicchallenge.config.Encrypt;
import com.globallogic.challenge.globallogicchallenge.dao.UsuarioDao;
import com.globallogic.challenge.globallogicchallenge.entity.User;
import com.globallogic.challenge.globallogicchallenge.exception.errorModels.BadRequestEx;
import com.globallogic.challenge.globallogicchallenge.exception.errorModels.InternalServerErrorEx;
import com.globallogic.challenge.globallogicchallenge.exception.errorModels.NotFoundEx;
import com.globallogic.challenge.globallogicchallenge.model.AuthenticationReq;
import com.globallogic.challenge.globallogicchallenge.model.LoginResponse;
import com.globallogic.challenge.globallogicchallenge.model.UserCreatedResponse;
import com.globallogic.challenge.globallogicchallenge.model.UserDto;
import com.globallogic.challenge.globallogicchallenge.service.UsuarioService;
import org.apache.logging.log4j.util.Strings;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioDao userDao;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private Encrypt encrypt;

    @Autowired
    private JwtUtilServiceImpl jwtUtilServiceImpl;

    @Override
    public UserCreatedResponse createUser(UserDto newUserDto) throws BadRequestEx, InternalServerErrorEx {

        User findUser = userDao.findByEmail(newUserDto.getEmail().toLowerCase());
        if(Objects.nonNull(findUser)){
            throw new BadRequestEx("This Username already exist.");
        }

        String jwt = jwtUtilServiceImpl.generateToken(
                        org.springframework.security.core.userdetails.User.withUsername(newUserDto.getEmail().toLowerCase())
                            .password(encrypt.getAES(newUserDto.getPassword()))
                            .roles(newUserDto.getRole().toString())
                            .build()
                     );

        if(Strings.isEmpty(jwt)){
            throw new InternalServerErrorEx("Token generation fail.");
        }

        User newUser = mapper.map(newUserDto, User.class);
        newUser.setEmail(newUserDto.getEmail().toLowerCase());
        newUser.setPassword(encrypt.getAES(newUserDto.getPassword()));
        newUser.setRole(newUserDto.getRole().toString());
        newUser.setCreated(LocalDateTime.now());
        newUser.setLastLogin(LocalDateTime.now());
        newUser.setIsActive(true);
        newUser.setToken(jwt);

        try {
            newUser = userDao.save(newUser);
            if(Objects.isNull(newUser)){
                throw new InternalServerErrorEx("Error has occurred while information was persisted.");
            }
        } catch (Exception ex) {
            throw new InternalServerErrorEx("Error has occurred while information was persisted - " + ex.getMessage());
        }

        UserCreatedResponse response = mapper.map(newUser, UserCreatedResponse.class);
        UserDto userDto = mapper.map(newUser, UserDto.class);

        response.setUser(userDto);

        return response;
    }

    @Override
    public LoginResponse login(AuthenticationReq req) throws NotFoundEx, BadRequestEx {

        User user = userDao.findByEmail(req.getUser());
        if(Objects.isNull(user)){
            throw new NotFoundEx("User not found.");
        }

        if(!user.getPassword().equals(encrypt.getAES(req.getPassword()))){
            throw new BadRequestEx("Incorrect Password. Please try again.");
        }

        String newToken = jwtUtilServiceImpl.generateToken(
                org.springframework.security.core.userdetails.User
                                .withUsername(req.getUser().toLowerCase())
                                .password(encrypt.getAES(req.getPassword()))
                                .roles(req.getRole().toString())
                                .build()
        );

        user.setToken(newToken);
        user.setLastLogin(LocalDateTime.now());

        user = userDao.save(user);

        LoginResponse response = mapper.map(user, LoginResponse.class);
        response.setPassword(encrypt.getAESDecrypt(response.getPassword()));

        return response;
    }

    public String getToken(String username) throws NotFoundEx {
        User user = userDao.findByEmail(username);
        if(Objects.isNull(user)){
            throw new NotFoundEx("User not found.");
        }

        return user.getToken();
    }
}
