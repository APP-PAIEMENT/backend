package com.example.demo.services;

import com.example.demo.models.Compte;
import com.example.demo.models.Transaction;
import com.example.demo.repository.CompteRepository;
import com.example.demo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CompteRepository compteRepository;

    /**
     * Créer une transaction entre deux comptes
     */
    public Transaction createTransaction(String compteSourceId, String compteDestinationId, double montant, String description) {
        if (montant <= 0) {
            throw new IllegalArgumentException("Le montant doit être supérieur à zéro.");
        }

        Compte compteSource = compteRepository.findById(compteSourceId)
                .orElseThrow(() -> new IllegalArgumentException("Compte source introuvable."));
        Compte compteDestination = compteRepository.findById(compteDestinationId)
                .orElseThrow(() -> new IllegalArgumentException("Compte destination introuvable."));

        if (compteSource.getBalance() < montant) {
            throw new IllegalArgumentException("Le solde du compte source est insuffisant.");
        }

        // Mise à jour des soldes
        compteSource.setBalance(compteSource.getBalance() - montant);
        compteDestination.setBalance(compteDestination.getBalance() + montant);

        // Sauvegarde des comptes
        compteRepository.save(compteSource);
        compteRepository.save(compteDestination);

        // Création de la transaction
        Transaction transaction = new Transaction();
        transaction.setCompteSource(compteSource);
        transaction.setCompteDestination(compteDestination);
        transaction.setMontant(montant);
       // transaction.setType("transfer");
        transaction.setDescription(description);

        return transactionRepository.save(transaction);
    }

    /**
     * Récupérer toutes les transactions
     */
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    /**
     * Récupérer les transactions d'un compte source
     */
    public List<Transaction> getTransactionsByCompteSourceId(String compteSourceId) {
        return transactionRepository.findByCompteSourceId(compteSourceId);
    }

    /**
     * Récupérer les transactions d'un compte destination
     */
    public List<Transaction> getTransactionsByCompteDestinationId(String compteDestinationId) {
        return transactionRepository.findByCompteDestinationId(compteDestinationId);
    }
}

