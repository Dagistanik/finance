package com.mycompany.currencyexchangeservice.model;

import com.mycompany.currencyexchangeservice.enums.ExpenseCategory;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "spending_limits")
public class SpendingLimit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "limit_setting_date")
    private LocalDate limitSettingDate;

    @Column(name = "limit_amount")
    private BigDecimal limitAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "expense_category")
    private ExpenseCategory expenseCategory;

    // Конструкторы, геттеры и сеттеры
}
