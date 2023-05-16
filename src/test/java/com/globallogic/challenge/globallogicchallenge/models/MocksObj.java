package com.globallogic.challenge.globallogicchallenge.models;

import com.globallogic.challenge.globallogicchallenge.entity.Phone;
import com.globallogic.challenge.globallogicchallenge.entity.User;
import com.globallogic.challenge.globallogicchallenge.model.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class MocksObj {

    public static UserDto createUserRequest() {
        PhoneDto phone = new PhoneDto();
        phone.setNumber(12348765L);
        phone.setCitycode(351);
        phone.setContrycode("54");

        UserDto userDto = new UserDto();
        userDto.setName("Test");
        userDto.setEmail("test@test.com");
        userDto.setPassword("LoginTest123");
        userDto.setRole(RoleEnum.ADMIN);
        userDto.setPhones(List.of(phone));

        return userDto;
    }

    public static User userEntity() {
        User user = new User();

        Phone phone = new Phone();
        phone.setNumber(12348765L);
        phone.setCitycode(351);
        phone.setContrycode("54");

        user.setId(UUID.randomUUID());
        user.setName("Test");
        user.setEmail("test@test.com");
        user.setPassword("AdEcYT6+5lh3OpiXjvgFJw==");
        user.setRole(RoleEnum.ADMIN.toString());
        user.setPhones(List.of(phone));
        user.setCreated(LocalDateTime.now());
        user.setLastLogin(LocalDateTime.now());
        user.setToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZ3VzbWFuY2FAZ21haWwuY29tIiwiZXhwIjoxNjg0MjMyMjgwLCJpYXQiOjE2ODQyMDM0ODAsInJvbCI6eyJhdXRob3JpdHkiOiJST0xFX0FETUlOIn19.UNV7ks2ywGooReP5LyG458PfDpkhoEriA4drMof8udE");
        user.setIsActive(true);

        return user;
    }

    public static UserCreatedResponse userResponse() {
        UserCreatedResponse userResponse = new UserCreatedResponse();
        userResponse.setId(UUID.randomUUID());
        userResponse.setUser(createUserRequest());
        userResponse.setCreated(LocalDateTime.now());
        userResponse.setLastLogin(LocalDateTime.now());
        userResponse.setToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZ3VzbWFuY2FAZ21haWwuY29tIiwiZXhwIjoxNjg0MjMyMjgwLCJpYXQiOjE2ODQyMDM0ODAsInJvbCI6eyJhdXRob3JpdHkiOiJST0xFX0FETUlOIn19.UNV7ks2ywGooReP5LyG458PfDpkhoEriA4drMof8udE");
        userResponse.setIsActive(true);

        return userResponse;
    }

    public static AuthenticationReq authenticationReq() {
        return new AuthenticationReq("test@test.com", "LoginTest123", RoleEnum.ADMIN);
    }

    public static LoginResponse loginResponse() {
        PhoneDto phone = new PhoneDto();
        phone.setNumber(12348765L);
        phone.setCitycode(351);
        phone.setContrycode("54");

        LoginResponse response = new LoginResponse();
        response.setId(UUID.randomUUID());
        response.setCreated(LocalDateTime.now());
        response.setLastLogin(LocalDateTime.now());
        response.setToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZ3VzbWFuY2FAZ21haWwuY29tIiwiZXhwIjoxNjg0MjMyMjgwLCJpYXQiOjE2ODQyMDM0ODAsInJvbCI6eyJhdXRob3JpdHkiOiJST0xFX0FETUlOIn19.UNV7ks2ywGooReP5LyG458PfDpkhoEriA4drMof8udE");
        response.setIsActive(true);
        response.setName("Test");
        response.setEmail("test@test.com");
        response.setPassword("AdEcYT6+5lh3OpiXjvgFJw==");
        response.setPhones(List.of(phone));

        return response;
    }
}
