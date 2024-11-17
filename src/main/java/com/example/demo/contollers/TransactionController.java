package com.example.demo.contollers;

import com.example.demo.models.Transaction;
import com.example.demo.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/transfer")
    public Transaction createTransfer(
            @RequestParam String compteSourceId,
            @RequestParam String compteDestinationId,
            @RequestParam double montant,
            @RequestParam(required = false) String description
    ) {
        return transactionService.createTransaction(compteSourceId, compteDestinationId, montant, description);
    }

    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/source/{compteSourceId}")
    public List<Transaction> getTransactionsByCompteSourceId(@PathVariable String compteSourceId) {
        return transactionService.getTransactionsByCompteSourceId(compteSourceId);
    }

    @GetMapping("/destination/{compteDestinationId}")
    public List<Transaction> getTransactionsByCompteDestinationId(@PathVariable String compteDestinationId) {
        return transactionService.getTransactionsByCompteDestinationId(compteDestinationId);
    }
}
