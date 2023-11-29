package com.mycompany.currencyexchangeservice.repository;

import com.mycompany.currencyexchangeservice.enums.ExpenseCategory;
import com.mycompany.currencyexchangeservice.model.SpendingLimit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpendingLimitRepository extends JpaRepository<SpendingLimit, Long> {
    List<SpendingLimit> findByExpenseCategory(ExpenseCategory expenseCategory);

}