package com.dev.domain.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonaJuridicaResponse {

    private String ruc;
    private String razon_social;
    private String estado;
    private String direccion;
    private String ubigeo;
    private String departamento;
    private String provincia;
    private String distrito;
}
