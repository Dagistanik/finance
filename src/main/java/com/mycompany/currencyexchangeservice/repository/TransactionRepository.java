package com.mycompany.currencyexchangeservice.repository;

import com.mycompany.currencyexchangeservice.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByLimitExceeded(Boolean limitExceeded);

    // Пример запроса для поиска транзакций с превышением лимита,
    // вам нужно будет адаптировать его под вашу логику и структуру данных
    @Query("SELECT t FROM Transaction t WHERE t.limitExceeded = true")
    List<Transaction> findAllTransactionsExceedingLimit();

}