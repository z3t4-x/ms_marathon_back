package com.dev.repository;

import com.dev.domain.Rol;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends MongoRepository<Rol, String> {

    Rol findByNombreRol(String nombreRol);
}
