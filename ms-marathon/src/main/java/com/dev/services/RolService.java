package com.dev.services;

import java.util.List;

import com.dev.domain.Rol;

public interface RolService {

	
	/**
	 * método para registrar rol
	 * @param rol
	 * @return rol
	 * @throws Exception
	 */

    Rol registrar(Rol rol) throws Exception;

    /**
     * buscar por id
     * @param id
     * @return
     * @throws Exception
     */
    Rol buscarPorId(String id) throws Exception;

}
