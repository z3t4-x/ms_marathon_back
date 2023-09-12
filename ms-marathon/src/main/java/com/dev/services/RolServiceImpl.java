package com.dev.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.domain.Rol;
import com.dev.repository.RolRepository;


@Service
public class RolServiceImpl implements RolService{
	
	@Autowired
	private RolRepository repo;
	
	/**
	 * 
	 */
	@Override
	public Rol registrar(Rol rol) throws Exception {
		
		return repo.save(rol);
	}

	/**
	 *
	 */
	@Override
	public Rol buscarPorId(String id) throws Exception {

		return repo.findById(id).orElse(null);
	}


}
