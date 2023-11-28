package com.mycompany.currencyexchangeservice.repository;

import com.mycompany.currencyexchangeservice.model.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Optional;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {
    Optional<ExchangeRate> findByCurrencyPairAndDate(String currencyPair, LocalDate date);

    @Query("SELECT e FROM ExchangeRate e WHERE e.currencyPair = :currencyPair AND e.date <= :date ORDER BY e.date DESC")
    Optional<ExchangeRate> findTopByCurrencyPairAndDateBeforeOrderByDateDesc(String currencyPair, LocalDate date);
}