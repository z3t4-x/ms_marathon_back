package com.dev.controllers;


import com.dev.domain.PersonaJuridica;
import com.dev.domain.response.PersonaJuridicaResponse;
import com.dev.exception.ServiceException;
import com.dev.services.PersonaJuridicaService;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PersonaJuridicaController {

    @Autowired
    private PersonaJuridicaService personaJuridicaService;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarPersonaJuridica(@RequestBody PersonaJuridica personaJuridica) throws Exception {
        try {
            PersonaJuridica personaJuridica2 = personaJuridicaService.obtenerInformacionPersonaJuridica(personaJuridica);

            return new ResponseEntity<>(personaJuridica2, HttpStatus.CREATED);

        } catch (ServiceException e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", e.getMessage()));

        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<PersonaJuridica>> listarPersonasJuridicas() throws Exception {
        List<PersonaJuridica> personasJuridicas = personaJuridicaService.listarPersonasJuridicas();
        return new ResponseEntity<>(personasJuridicas, HttpStatus.OK);
    }


    @GetMapping("/{ruc}")
    public ResponseEntity<?> obtenerInformacionPersonaJuridica(@PathVariable String ruc) {
        try {
            PersonaJuridicaResponse response = personaJuridicaService.obtenerPersonaJur(ruc);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (ServiceException e) {
            // Captura la excepción de ServiceException
            String mensajeError = e.getMessage(); // Obtén el mensaje de error

            // Devuelve el mensaje de error en el cuerpo de la respuesta HTTP
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensajeError);
        } catch (Exception ex) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }
}
