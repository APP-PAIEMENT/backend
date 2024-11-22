package com.example.demo.repository;

import com.example.demo.models.Compte;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CompteRepository extends MongoRepository<Compte, String> {
    List<Compte> findByUserId(String userId);
    Optional<Compte> findByNumerousCompte(String numerousCompte);
}
