package com.dev.repository;

import com.dev.domain.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {

// Usuario findByCodigoUsuario(String codigoUsuario);


 boolean existsByCorreo(String email);

 Optional<Usuario> findByCodigoUsuario(String codigoUsuario);

 boolean existsByCodigoUsuario(String codigoUsuario);


}
