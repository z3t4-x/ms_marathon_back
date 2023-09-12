package com.dev.repository;

import com.dev.domain.PersonaJuridica;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PersonaJuridicaRepository extends MongoRepository<PersonaJuridica, String> {


    Boolean existsByRuc(String ruc);
}
