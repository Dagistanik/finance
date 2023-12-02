package com.mycompany.currencyexchangeservice.controller;

import com.mycompany.currencyexchangeservice.model.Transaction;
import com.mycompany.currencyexchangeservice.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/exceeding-limits")
    public ResponseEntity<List<Transaction>> getTransactionsExceedingLimits() {
        List<Transaction> transactions = transactionService.getTransactionsExceedingLimits();
        return ResponseEntity.ok(transactions);
    }

    @PostMapping
    public ResponseEntity<Transaction> recordTransaction(@RequestBody Transaction transaction) {
        Transaction newTransaction = transactionService.recordTransaction(transaction);
        return ResponseEntity.ok(newTransaction);
    }
}