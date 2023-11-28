package com.mycompany.currencyexchangeservice.repository;

import com.mycompany.currencyexchangeservice.model.SpendingLimit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpendingLimitRepository extends JpaRepository<SpendingLimit, Long> {
    // Методы для работы с лимитами расходов
}