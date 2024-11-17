package com.example.demo.services;

import com.example.demo.models.TypeCompte;
import com.example.demo.repository.TypeCompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeCompteService {
    @Autowired
    private TypeCompteRepository repository;

    public TypeCompte createTypeCompte(TypeCompte typeCompte) {
        return repository.save(typeCompte);
    }

    public List<TypeCompte> getAllTypeComptes() {
        return repository.findAll();
    }

    public Optional<TypeCompte> getTypeCompteById(String id) {
        return repository.findById(id);
    }

    public TypeCompte updateTypeCompte(String id, TypeCompte updatedTypeCompte) {
        return repository.findById(id)
                .map(existingTypeCompte -> {
                    existingTypeCompte.setName(updatedTypeCompte.getName());
                    return repository.save(existingTypeCompte);
                })
                .orElseThrow(() -> new IllegalArgumentException("TypeCompte not found"));
    }

    public void deleteTypeCompte(String id) {
        repository.deleteById(id);
    }
}
