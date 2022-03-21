package com.example.api.curso.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String identificacion;
    private String nombres;
    private String username;
    private String email;
    private String idRol;
    private String password;
    private String empresa;
}
