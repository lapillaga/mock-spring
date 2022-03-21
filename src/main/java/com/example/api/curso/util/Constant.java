package com.example.api.curso.util;

import com.example.api.curso.domain.Rol;
import com.example.api.curso.domain.User;

import java.util.ArrayList;
import java.util.List;

public class Constant {
    private Constant() {
    }

    public static final List<Rol> ROLES = List.of(
            new Rol("VER", "Verificador", "Verificador de documentos"),
            new Rol("COR", "Coordinador", "Coordinador de proyectos")
    );
    public static final String SUCCESS = "success";
    public static final String EMAIL_NOT_FOUND = "email_not_found";
    public static final String INCORRECT_PASSWORD = "incorrect_password";
    public static final String TOKEN = "SuperSecretToken";

    public static List<User> users = new ArrayList<>(){{
        add(new User(
                "0301971495",
                "LUIS ANTONIO PILLAGA ZHAGÑAY",
                "lpillaga",
                "lpillaga@pichincha.com",
                ROLES.get(0),
                "La$$$Password34",
                "Devsu Software"
        ));
        add(new User(
                "1701971495",
                "DIEGO ANDRES MAIGUALCA TOAPANTA",
                "damaigualca",
                "damaigualca@pichincha.com",
                ROLES.get(0),
                "Da$$$Password87",
                "ESPE"
        ));
        add(new User(
                "0387371495",
                "ROXANA LISBETH PEREZ TIPAN",
                "rltorres",
                "rltorres@pichincha.com",
                ROLES.get(1),
                "Li$$$Password45",
                "Banco Pichincha"
        ));
    }};
}
