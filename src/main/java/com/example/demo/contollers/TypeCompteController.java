package com.example.demo.contollers;

import com.example.demo.models.TypeCompte;
import com.example.demo.services.TypeCompteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/type-comptes")
public class TypeCompteController {
    @Autowired
    private TypeCompteService service;

    @PostMapping
    public TypeCompte createTypeCompte(@Valid @RequestBody TypeCompte typeCompte) {
        return service.createTypeCompte(typeCompte);
    }

    @GetMapping
    public List<TypeCompte> getAllTypeComptes() {
        return service.getAllTypeComptes();
    }

    @GetMapping("/{id}")
    public Optional<TypeCompte> getTypeCompteById(@PathVariable String id) {
        return service.getTypeCompteById(id);
    }

    @PutMapping("/{id}")
    public TypeCompte updateTypeCompte(@PathVariable String id, @Valid @RequestBody TypeCompte updatedTypeCompte) {
        return service.updateTypeCompte(id, updatedTypeCompte);
    }

    @DeleteMapping("/{id}")
    public String deleteTypeCompte(@PathVariable String id) {
        service.deleteTypeCompte(id);
        return "TypeCompte with ID " + id + " has been deleted.";
    }
}
