package com.globallogic.challenge.globallogicchallenge.service.impl;

import com.globallogic.challenge.globallogicchallenge.config.Encrypt;
import com.globallogic.challenge.globallogicchallenge.dao.UsuarioDao;
import com.globallogic.challenge.globallogicchallenge.exception.errorModels.BadRequestEx;
import com.globallogic.challenge.globallogicchallenge.exception.errorModels.InternalServerErrorEx;
import com.globallogic.challenge.globallogicchallenge.exception.errorModels.NotFoundEx;
import com.globallogic.challenge.globallogicchallenge.model.AuthenticationReq;
import com.globallogic.challenge.globallogicchallenge.model.LoginResponse;
import com.globallogic.challenge.globallogicchallenge.model.UserCreatedResponse;
import com.globallogic.challenge.globallogicchallenge.models.MocksObj;
import com.globallogic.challenge.globallogicchallenge.service.JwtUtilService;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.modelmapper.ModelMapper;
import org.springframework.test.util.ReflectionTestUtils;

class UsuarioServiceImplTest {

    final String JWT = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZ3VzbWFuY2FAZ21haWwuY29tIiwiZXhwIjoxNjg0MjMyMjgwLCJpYXQiOjE2ODQyMDM0ODAsInJvbCI6eyJhdXRob3JpdHkiOiJST0xFX0FETUlOIn19.UNV7ks2ywGooReP5LyG458PfDpkhoEriA4drMof8udE";

    @Mock
    private UsuarioDao userDao = Mockito.mock(UsuarioDao.class);

    @Mock
    private Encrypt encrypt = Mockito.mock(Encrypt.class);

    @Mock
    private JwtUtilService jwtUtilService = Mockito.mock(JwtUtilService.class);;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(usuarioService, "mapper", new ModelMapper());
    }

    @Test
    void createUser_OK() throws InternalServerErrorEx, BadRequestEx {
        Mockito.when(userDao.findByEmail(ArgumentMatchers.any())).thenReturn(null);
        Mockito.when(jwtUtilService.generateToken(ArgumentMatchers.any())).thenReturn(JWT);
        Mockito.when(encrypt.getAES(ArgumentMatchers.any())).thenReturn("AdEcYT6+5lh3OpiXjvgFJw==");
        Mockito.when(userDao.save(ArgumentMatchers.any())).thenReturn(MocksObj.userEntity());

        UserCreatedResponse response = usuarioService.createUser(MocksObj.createUserRequest());

        Assertions.assertEquals(response.getUser().getEmail(), MocksObj.userEntity().getEmail());
    }

    @Test
    void createUser_Error_userAlreadyExist() {
        Mockito.when(userDao.findByEmail(ArgumentMatchers.any())).thenReturn(MocksObj.userEntity());

        Assertions.assertThrows(BadRequestEx.class, () -> {
            usuarioService.createUser(MocksObj.createUserRequest());
        });
    }

    @Test
    void createUser_error_EmptyToken() {
        Mockito.when(userDao.findByEmail(ArgumentMatchers.any())).thenReturn(null);
        Mockito.when(jwtUtilService.generateToken(ArgumentMatchers.any())).thenReturn(Strings.EMPTY);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            usuarioService.createUser(MocksObj.createUserRequest());
        });
    }

    @Test
    void createUser_error_PersistError() {
        Mockito.when(userDao.findByEmail(ArgumentMatchers.any())).thenReturn(null);
        Mockito.when(jwtUtilService.generateToken(ArgumentMatchers.any())).thenReturn(JWT);
        Mockito.when(encrypt.getAES(ArgumentMatchers.any())).thenReturn("AdEcYT6+5lh3OpiXjvgFJw==");
        Mockito.when(userDao.save(ArgumentMatchers.any())).thenReturn(null);

        Assertions.assertThrows(InternalServerErrorEx.class, () -> {
            usuarioService.createUser(MocksObj.createUserRequest());
        });
    }

    @Test
    void login_ok() throws NotFoundEx, BadRequestEx {
        Mockito.when(userDao.findByEmail(ArgumentMatchers.any())).thenReturn(MocksObj.userEntity());
        Mockito.when(encrypt.getAES(ArgumentMatchers.any())).thenReturn("AdEcYT6+5lh3OpiXjvgFJw==");
        Mockito.when(jwtUtilService.generateToken(ArgumentMatchers.any())).thenReturn(JWT);
        Mockito.when(userDao.save(ArgumentMatchers.any())).thenReturn(MocksObj.userEntity());

        LoginResponse response = usuarioService.login(MocksObj.authenticationReq());

        Assertions.assertEquals(response.getEmail(), MocksObj.userEntity().getEmail());
    }

    @Test
    void login_error_userNotFound() {
        Mockito.when(userDao.findByEmail(ArgumentMatchers.any())).thenReturn(null);

        Assertions.assertThrows(NotFoundEx.class, () -> {
            usuarioService.login(MocksObj.authenticationReq());
        });
    }

    @Test
    void login_error_pswNotMatch()  {
        AuthenticationReq req = MocksObj.authenticationReq();
        req.setPassword("asdqwezxc");

        Mockito.when(userDao.findByEmail(ArgumentMatchers.any())).thenReturn(MocksObj.userEntity());

        Assertions.assertThrows(BadRequestEx.class, () -> {
            usuarioService.login(req);
        });
    }

    @Test
    void getToken_ok() throws NotFoundEx {
        Mockito.when(userDao.findByEmail(ArgumentMatchers.any())).thenReturn(MocksObj.userEntity());
        String response = usuarioService.getToken(MocksObj.userEntity().getEmail());
        Assertions.assertEquals(response, JWT);
    }

    @Test
    void getToken_error_userNotFound() {
        Mockito.when(userDao.findByEmail(ArgumentMatchers.any())).thenReturn(null);

        Assertions.assertThrows(NotFoundEx.class, () -> {
            usuarioService.getToken(MocksObj.userEntity().getEmail());
        });
    }
}