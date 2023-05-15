package com.globallogic.challenge.globallogicchallenge.service.impl;

import com.globallogic.challenge.globallogicchallenge.config.Encrypt;
import com.globallogic.challenge.globallogicchallenge.dao.UsuarioDao;
import com.globallogic.challenge.globallogicchallenge.entity.Usuario;
import com.globallogic.challenge.globallogicchallenge.exception.errorModels.BadRequestEx;
import com.globallogic.challenge.globallogicchallenge.exception.errorModels.InternalServerErrorEx;
import com.globallogic.challenge.globallogicchallenge.model.UsuarioCreadoResponse;
import com.globallogic.challenge.globallogicchallenge.model.UsuarioDto;
import com.globallogic.challenge.globallogicchallenge.service.UsuarioService;
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

    @Override
    public UsuarioCreadoResponse createUser(UsuarioDto newUserDto) throws BadRequestEx, InternalServerErrorEx {

        Usuario findUser = userDao.findByEmail(newUserDto.getEmail().toLowerCase());
        if(Objects.nonNull(findUser)){
            throw new BadRequestEx("This Username already exist.");
        }

        Usuario newUser = mapper.map(newUserDto, Usuario.class);
        newUser.setEmail(newUserDto.getEmail().toLowerCase());
        newUser.setPassword(encrypt.getAES(newUserDto.getPassword()));
        newUser.setCreated(LocalDateTime.now());
        newUser.setLastLogin(LocalDateTime.now());
        newUser.setIsActive(true);
        newUser.setToken("");

        try {
            newUser = userDao.save(newUser);
            if(Objects.isNull(newUser)){
                throw new InternalServerErrorEx("Error has occurred while information was persisted.");
            }
        } catch (Exception ex) {
            throw new InternalServerErrorEx("Error has occurred while information was persisted - " + ex.getMessage());
        }

        UsuarioCreadoResponse response = mapper.map(newUser, UsuarioCreadoResponse.class);
        UsuarioDto userDto = mapper.map(newUser, UsuarioDto.class);

        response.setUsuario(userDto);

        return response;
    }
}
