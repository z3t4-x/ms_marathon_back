package com.dev.domain;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Document(collection = "usuarios")
public class Usuario {

    @Id
    private String idUsuario;

    private String codigoUsuario;

    private String correo;

    private String contrasenia;

    @DBRef
    private List<Rol> lstRoles = new ArrayList<>();
}
