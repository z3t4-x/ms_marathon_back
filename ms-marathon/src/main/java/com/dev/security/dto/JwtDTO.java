package com.dev.security.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtDTO {

	
    private String token;
    private String bearer = "Bearer";
    private String codigoUsuario;
    private Collection<? extends GrantedAuthority> authorities;


    public JwtDTO(String token, String codigoUsuario, Collection<? extends GrantedAuthority> authorities) {
        this.token = token;
        this.codigoUsuario = codigoUsuario;
        this.authorities = authorities;
    }

}
