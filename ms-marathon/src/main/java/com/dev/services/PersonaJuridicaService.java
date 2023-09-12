package com.dev.services;

import com.dev.domain.PersonaJuridica;
import com.dev.domain.response.PersonaJuridicaResponse;

import java.util.List;

public interface PersonaJuridicaService {

    /**
     * método que  obtiene la información del response y guarda
     * en la bd de mongo
     * @param personaJuridica
     * @return
     */
    PersonaJuridica obtenerInformacionPersonaJuridica(PersonaJuridica personaJuridica) throws Exception;

    /**
     * método para listar las personas registradas
     * @return
     */
    List<PersonaJuridica> listarPersonasJuridicas() throws Exception;

    PersonaJuridicaResponse obtenerPersonaJur(String ruc) throws Exception;
}
