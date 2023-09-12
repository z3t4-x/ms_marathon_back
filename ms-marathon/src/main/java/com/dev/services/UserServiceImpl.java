package com.dev.services;

import java.util.List;
import java.util.Optional;

import com.dev.domain.Rol;
import com.dev.domain.Usuario;
import com.dev.exception.ServiceException;
import com.dev.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository repo;


	@Override
	public boolean existeCorreo(String email) {
		return false;
	}

	/**
	 * 
	 */
	@Override
	public Usuario registrar(Usuario usuario) throws Exception {

		Boolean existeUsuarioName = repo.existsByCodigoUsuario(usuario.getCodigoUsuario());
		//valida si existe el username
		if(Boolean.TRUE.equals(existeUsuarioName)) {
			
			throw new ServiceException("El usersename: "+ usuario.getCodigoUsuario() +" ya existe");
		}
		
		Boolean existeEmail = repo.existsByCorreo(usuario.getCorreo());
		//valida si existe el email
		if(Boolean.TRUE.equals(existeEmail)) {
			
			throw new ServiceException("El email que intenta registrar ya existe: " + usuario.getCorreo());
		}

		return repo.save(usuario);
	}

	@Override
	public List<Rol> obtenerRolesUsuario() {
		return null;
	}


	/**
	 *
	 */
	@Override
	public Optional<Usuario> obtenerUsuario(String username) {
		
		
		return repo.findByCodigoUsuario(username);
	}

	/**
	 * 
	 */
	@Override
	public boolean existeUsuario(String username) {

		return repo.existsByCodigoUsuario(username);
	}





}
