package com.example.api.curso.domain;

import com.example.api.curso.util.Constant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private String identificacion;
    private String nombres;
    private String username;
    private String email;
    private Rol rol;
    @JsonIgnore
    private String password;
    private String empresa;

    public User fromUserRequest(UserRequest userRequest) {
        return User.builder()
                .identificacion(userRequest.getIdentificacion())
                .nombres(userRequest.getNombres())
                .username(userRequest.getUsername())
                .email(userRequest.getEmail())
                .rol(Constant.ROLES.stream().filter(r -> r.getCodigo().equals(userRequest.getIdRol())).findFirst().orElse(null))
                .password(userRequest.getPassword())
                .empresa(userRequest.getEmpresa())
                .build();
    }
}
