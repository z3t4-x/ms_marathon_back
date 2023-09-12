package com.dev.services;

import com.dev.client.RucServiceClient;
import com.dev.domain.PersonaJuridica;
import com.dev.domain.response.PersonaJuridicaResponse;
import com.dev.exception.ServiceException;
import com.dev.repository.PersonaJuridicaRepository;
import com.dev.utils.Constantes;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PersonaJuridicaServiceImpl implements PersonaJuridicaService {

    @Autowired
    private RucServiceClient client;

    @Autowired
    private PersonaJuridicaRepository repository;

    /**
     *
     */
    @Override
    public PersonaJuridica obtenerInformacionPersonaJuridica(PersonaJuridica personaJuridica) throws Exception{

        Boolean existeRuc = repository.existsByRuc(personaJuridica.getRuc());

        if(Boolean.TRUE.equals(existeRuc)){
            throw new ServiceException(Constantes.ErroresService.ERROR_RUC_EXISTENTE);
        }

         return repository.save(personaJuridica);

    }


    /**
     *
     */
    @Override
    public List<PersonaJuridica> listarPersonasJuridicas() {
        return repository.findAll();
    }


    /**
     *
     */
    @Override
  public PersonaJuridicaResponse obtenerPersonaJur(String ruc) {

        try {
            String token = "cXdlcnR5bGFtYXJja19zYUBob3RtYWlsLmNvbXF3ZXJ0eQ==";
            PersonaJuridicaResponse response = client.obtenerInformacionPersonaJuridica(2, ruc, token);
            return response;
        } catch (FeignException e) {
            log.error("Error al llamar al servicio externo: " + e.getMessage(), e);
            throw new ServiceException("Error al obtener información de la persona jurídica desde el servicio externo" + e.getMessage());
        }
    }

}
