package com.dev.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



import com.dev.services.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.domain.Rol;

@RestController
@RequestMapping("/roles")
public class RolController {

    @Autowired
    private RolService rolService;

    @PostMapping("/")
    public ResponseEntity<?> registrarRol(@RequestBody Rol rol, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            // Si hay errores de validación, puedes crear un mapa de mensajes de error
            Map<String, String> errores = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errores.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errores);
        }
        
        Rol nuevoRol = rolService.registrar(rol);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoRol);
    }



}