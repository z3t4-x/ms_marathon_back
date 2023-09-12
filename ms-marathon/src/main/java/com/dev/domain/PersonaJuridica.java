package com.dev.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "persona_juridica")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PersonaJuridica {

    @Id
    private String idPersonaJuridica;

    @Indexed(unique = true)
    private String ruc;

    private String razon_social;

    private String estado;

    private String direccion;

    private String ubigeo;

    private String departamento;

    private String provincia;

    private String distrito;

}
