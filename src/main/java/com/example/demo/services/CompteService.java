package com.example.demo.services;

import com.example.demo.models.Compte;
import com.example.demo.repository.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompteService {
    @Autowired
    private CompteRepository repository;

    public Compte createCompte(Compte compte) {
        return repository.save(compte);
    }

    public List<Compte> getAllComptes() {
        return repository.findAll();
    }

    public Optional<Compte> getCompteById(String id) {
        return repository.findById(id);
    }

    public List<Compte> getComptesByUserId(String userId) {
        return repository.findByUserId(userId);
    }

    public Compte updateCompte(String id, Compte updatedCompte) {
        return repository.findById(id)
                .map(existingCompte -> {
                    existingCompte.setBalance(updatedCompte.getBalance());
                    existingCompte.setTypeCompte(updatedCompte.getTypeCompte());
                    return repository.save(existingCompte);
                })
                .orElseThrow(() -> new IllegalArgumentException("Compte not found"));
    }

    public void deleteCompte(String id) {
        repository.deleteById(id);
    }
}
