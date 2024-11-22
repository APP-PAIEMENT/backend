package com.example.demo.services;

import com.example.demo.models.Compte;
import com.example.demo.models.Transaction;
import com.example.demo.models.User;
import com.example.demo.repository.CompteRepository;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.repository.UserRepository;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CompteRepository compteRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Créer une transaction entre deux comptes
     */
    public Transaction createTransaction(Transaction transaction) {
        if (transaction.getMontant() <= 0) {
            throw new IllegalArgumentException("Le montant doit être supérieur à zéro.");
        }

        Compte compteSource = compteRepository.findByNumerousCompte(transaction.getCompteSource())
                .orElseThrow(() -> new IllegalArgumentException("Compte source introuvable."));

        Compte compteDestination = compteRepository.findByNumerousCompte(transaction.getCompteDestination())
                .orElseThrow(() -> new IllegalArgumentException("Compte destination introuvable."));

        if (compteSource.getBalance() < transaction.getMontant()) {
            throw new IllegalArgumentException("Le solde du compte source est insuffisant.");
        }

        // Mise à jour des soldes
        compteSource.setBalance(compteSource.getBalance() - transaction.getMontant());
        compteDestination.setBalance(compteDestination.getBalance() + transaction.getMontant());

        // Sauvegarde des comptes
        compteRepository.save(compteSource);
        compteRepository.save(compteDestination);

        // Création de la transaction
        Transaction newTransaction = new Transaction();
        newTransaction.setCompteSource(compteSource.getNumerousCompte());
        newTransaction.setCompteDestination(compteDestination.getNumerousCompte());
        newTransaction.setMontant(transaction.getMontant());

        return transactionRepository.save(newTransaction);
    }


    public List<Transaction> getTransactionsByUser(String userId) {


        // Liste pour stocker toutes les transactions
        List<Transaction> allTransactions = new ArrayList<>();
            // Récupérer l'utilisateur associé à ce compte
            Optional<User> userOption = userRepository.findById(userId);
            if (userOption.isPresent()) {
                User user = userOption.get();
                List<Compte> comptes = compteRepository.findByUserId(user.getId());

                // Parcourir les comptes et récupérer les transactions pour chaque compte
                for (Compte c : comptes) {
                    // Obtenir les transactions du compte source
                    List<Transaction> transactions = getTransactionsByCompteSourceId(c.getNumerousCompte());

                    // Ajouter toutes les transactions de ce compte à la liste globale
                    allTransactions.addAll(transactions);
                }
            }

        // Retourner toutes les transactions collectées
        return allTransactions;
    }
    public double getBalanceByUser(String userId) {

        // Liste pour stocker toutes les transactions
        double balance = 0;
        // Récupérer l'utilisateur associé à ce compte
        Optional<User> userOption = userRepository.findById(userId);
        if (userOption.isPresent()) {
            User user = userOption.get();
            List<Compte> comptes = compteRepository.findByUserId(user.getId());

            // Parcourir les comptes et récupérer les transactions pour chaque compte
            for (Compte c : comptes) {
                // Obtenir les transactions du compte source
                balance+=  c.getBalance();
            }
        }

        // Retourner toutes les transactions collectées
        return balance;
    }

    /**
     * Récupérer toutes les transactions
     */
//    public List<Transaction> getAllTransactions() {
//        return transactionRepository.findAll();
//    }

    /**
     * Récupérer les transactions d'un compte source
     */
    public List<Transaction> getTransactionsByCompteSourceId(String compteSourceId) {
        return transactionRepository.findByCompteSource(compteSourceId);
    }

   /**
     * Récupérer les transactions d'un compte destination
     */
//    public List<Transaction> getTransactionsByCompteDestinationId(String compteDestinationId) {
//        return transactionRepository.findByCompteDestinationId(compteDestinationId);
//    }
}

