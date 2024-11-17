package com.example.demo.repository;

import com.example.demo.models.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
    List<Transaction> findByCompteSourceId(String compteSourceId);
    List<Transaction> findByCompteDestinationId(String compteDestinationId);
}
