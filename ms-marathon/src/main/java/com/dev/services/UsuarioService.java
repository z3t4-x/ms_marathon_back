package com.dev.services;

import java.util.List;
import java.util.Optional;

import com.dev.domain.Rol;
import com.dev.domain.Usuario;

public interface UsuarioService {
	
	
 /**
  * método para validar si existe el correo.
  * @param email
  * @return
  */
    boolean existeCorreo(String email);

	/**
	 * Método registrar
	 * @param usuario
	 * @return
	 * @throws Exception
	 */
	    Usuario registrar(Usuario usuario) throws Exception;


    List<Rol> obtenerRolesUsuario();

    /**
     * 
     * @param username
     * @return
     */
    Optional<Usuario> obtenerUsuario(String username);
    
    
    boolean existeUsuario(String username);
    

}
