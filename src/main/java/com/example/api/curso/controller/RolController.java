package com.example.api.curso.controller;

import com.example.api.curso.domain.Rol;
import com.example.api.curso.util.Constant;
import com.example.api.curso.util.CryptPassword;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
public class RolController {
    private final CryptPassword api = new CryptPassword();

    @GetMapping("")
    public List<Rol> getRoles(){
        return Constant.ROLES;
    }
}
