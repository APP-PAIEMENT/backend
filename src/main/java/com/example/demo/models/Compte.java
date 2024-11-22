package com.example.demo.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "comptes")
public class Compte {
    @Id
    private String id;

    private String userId; // Référence à l'utilisateur propriétaire du compte

    private String typeCompteId; // Référence au type de compte

    private double balance; // Solde du compte
    @NotBlank(message = "numerousCompte is required")
    @Size(min = 9, message = "numerousCompte must be at least 9 characters")
    private  String numerousCompte;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTypeCompteId() {
        return typeCompteId;
    }

    public void setTypeCompteId(String typeCompteId) {
        this.typeCompteId = typeCompteId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getNumerousCompte() {
        return numerousCompte;
    }

    public void setNumerousCompte(Number numerousCompte) {
        this.numerousCompte = numerousCompte.toString();
    }
}
