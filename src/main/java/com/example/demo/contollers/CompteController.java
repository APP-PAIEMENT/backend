package com.example.demo.contollers;

import com.example.demo.models.Compte;
import com.example.demo.services.CompteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comptes")
public class CompteController {
    @Autowired
    private CompteService service;

    @PostMapping
    public Compte createCompte(@Valid @RequestBody Compte compte) {
        return service.createCompte(compte);
    }

    @GetMapping
    public List<Compte> getAllComptes() {
        return service.getAllComptes();
    }

    @GetMapping("/{id}")
    public Optional<Compte> getCompteById(@PathVariable String id) {
        return service.getCompteById(id);
    }

    @GetMapping("/user/{userId}")
    public List<Compte> getComptesByUserId(@PathVariable String userId) {
        return service.getComptesByUserId(userId);
    }

//    @PutMapping("/{id}")
//    public Compte updateCompte(@PathVariable String id, @Valid @RequestBody Compte updatedCompte) {
//        return service.updateCompte(id, updatedCompte);
//    }

    @DeleteMapping("/{id}")
    public String deleteCompte(@PathVariable String id) {
        service.deleteCompte(id);
        return "Compte with ID " + id + " has been deleted.";
    }
}
