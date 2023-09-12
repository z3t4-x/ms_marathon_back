package com.dev.security;


import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.dev.domain.Usuario;
import com.dev.services.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;






@Service
public class UserDetailsServiceImpl  implements UserDetailsService{

    @Autowired
    private UsuarioService userService;
	
	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        Usuario usuario = userService.obtenerUsuario(usernameOrEmail)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username or email: "+ usernameOrEmail));

        Set<GrantedAuthority> authorities = usuario.getLstRoles()
                .stream()
                .map((role) -> new SimpleGrantedAuthority(role.getNombreRol())).collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(usuario.getCorreo(),
                usuario.getContrasenia(),
                authorities);
    }
	}




