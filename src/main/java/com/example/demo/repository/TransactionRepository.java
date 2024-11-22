package com.example.demo.repository;

import com.example.demo.models.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
   List<Transaction> findByCompteSource(String compteSource);
}
