package com.mycompany.currencyexchangeservice.repository;

import com.mycompany.currencyexchangeservice.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByLimitExceeded(Boolean limitExceeded);

    @Query("SELECT t FROM Transaction t WHERE t.limitExceeded = true")
    List<Transaction> findAllTransactionsExceedingLimit();


    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.transactionDate <= :date")
    BigDecimal sumAllTransactionsUntil(@Param("date") LocalDate date, LocalDate transactionDate);

}