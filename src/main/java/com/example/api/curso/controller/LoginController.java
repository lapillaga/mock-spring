package com.example.api.curso.controller;

import com.example.api.curso.domain.LoginRequest;
import com.example.api.curso.domain.LoginResponse;
import com.example.api.curso.domain.User;
import com.example.api.curso.util.Constant;
import com.example.api.curso.util.CryptPassword;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/login")
public class LoginController {
    private final CryptPassword api = new CryptPassword();

    @PostMapping
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        List<User> users = Constant.users;
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setStatus(Constant.SUCCESS);
        loginResponse.setMessage("Inicio de sesión exitoso");
        boolean existUser = users.stream().anyMatch(user -> user.getEmail().equals(loginRequest.getEmail()));
        if (!existUser) {
            loginResponse.setStatus(Constant.EMAIL_NOT_FOUND);
            loginResponse.setMessage("El usuario no existe");
            return loginResponse;
        }
        boolean passwordCorrect = users.stream().anyMatch(user ->  user.getPassword().equals(loginRequest.getPassword()));
        if (!passwordCorrect) {
            loginResponse.setStatus(Constant.INCORRECT_PASSWORD);
            loginResponse.setMessage("La contraseña es incorrecta");
            return loginResponse;
        }
        loginResponse.setToken(api.md5(Constant.TOKEN));
        loginResponse.setUser(users.stream().filter(user -> user.getEmail().equals(loginRequest.getEmail())).findFirst().get());
        return loginResponse;
    }

}