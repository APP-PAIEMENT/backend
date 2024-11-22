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
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionService.createTransaction(transaction);
    }
    @GetMapping("/transfer/{id}")
    public List<Transaction> getTransactionByUser(@PathVariable String id){
        return transactionService.getTransactionsByUser(id);
    }

    @GetMapping("/balance/{id}")
    public double getBalanceByUser(@PathVariable String id){
        return transactionService.getBalanceByUser(id);
    }

//    @GetMapping
//    public List<Transaction> getAllTransactions() {
//        return transactionService.getAllTransactions();
//    }

//    @GetMapping("/source/{compteSourceId}")
//    public List<Transaction> getTransactionsByCompteSourceId(@PathVariable String compteSourceId) {
//        return transactionService.getTransactionsByCompteSourceId(compteSourceId);
//    }

//    @GetMapping("/destination/{compteDestinationId}")
//    public List<Transaction> getTransactionsByCompteDestinationId(@PathVariable String compteDestinationId) {
//        return transactionService.getTransactionsByCompteDestinationId(compteDestinationId);
//    }
}
