package com.example.demo.repository;

import com.example.demo.models.TypeCompte;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TypeCompteRepository extends MongoRepository<TypeCompte, String> {
    // Méthodes de recherche personnalisées pour TypeCompte
}
