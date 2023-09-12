package com.dev.security.controller;

import java.util.*;



import com.dev.domain.Rol;

import com.dev.domain.Usuario;
import com.dev.exception.ServiceException;
import com.dev.security.JwtProvider;
import com.dev.security.dto.JwtDTO;
import com.dev.security.dto.LoginUsuario;
import com.dev.services.RolService;
import com.dev.services.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolService rolService;

    @Autowired
    JwtProvider jwtProvider;


    @PostMapping("/nuevoUsuario")
    public ResponseEntity<?> nuevo(@RequestBody Usuario user, BindingResult bindingResult) throws Exception {


        Map<String, String> errores = new HashMap<>();
        
        // Validación de errores del modelo.
        if(bindingResult.hasErrors()){
            for (FieldError error : bindingResult.getFieldErrors()) {
                errores.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(Collections.singletonMap("errores", bindingResult.getFieldErrors()));
        }

        try {
        user.setContrasenia(passwordEncoder.encode(user.getContrasenia()));

        List<Rol> roles = new ArrayList<>() ;

        for (Rol rol : user.getLstRoles()) {

            roles.add(rolService.buscarPorId(rol.getId()));

        }

        user.setLstRoles(roles);
  
         usuarioService.registrar(user);
        return  ResponseEntity.status(HttpStatus.CREATED).body(user);
        
        } catch (ServiceException e) {
            errores.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", e.getMessage()));
        }
        catch (Exception e) {
            errores.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", e.getMessage()));
        }
        

    }



    
    
    /**
     * Login
     * @param loginUsuario
     * @param bindingResult
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUsuario loginUsuario, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return new ResponseEntity<>("Campos mal puestos.", HttpStatus.BAD_REQUEST);
//        }
//        
    	Map<String, String> errores = new HashMap<>();
        if(bindingResult.hasErrors()){
            for (FieldError error : bindingResult.getFieldErrors()) {
                errores.put(error.getField(), error.getDefaultMessage());
            }
            log.error(" error: " + bindingResult.getFieldErrors());
           
            return ResponseEntity.badRequest().body(Collections.singletonMap("errores", bindingResult.getFieldErrors()));
        }

        Usuario usuario = usuarioService.obtenerUsuario(loginUsuario.getCodigoUsuario()).orElse(null);

        if (usuario == null) {
            return new ResponseEntity<>("El usuario no existe.", HttpStatus.UNAUTHORIZED);
        }  else {
            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getCodigoUsuario(), loginUsuario.getContrasenia()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtProvider.generateToken(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            JwtDTO jwtDto = new JwtDTO(jwt, userDetails.getUsername(), userDetails.getAuthorities());
            return new ResponseEntity<>(jwtDto, HttpStatus.OK);
        }
    }



}