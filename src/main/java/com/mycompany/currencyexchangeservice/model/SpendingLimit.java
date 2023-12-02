package com.mycompany.currencyexchangeservice.model;

import com.mycompany.currencyexchangeservice.enums.ExpenseCategory;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
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

    public void setLimitSettingDate(LocalDate limitSettingDate) {
        LocalDate today = LocalDate.now();
        if (limitSettingDate.isAfter(today)) {
            throw new IllegalArgumentException("Limit setting date cannot be in the future.");
        }
        if (limitSettingDate.isBefore(today)) {
            throw new IllegalArgumentException("Limit setting date cannot be in the past.");
        }
        this.limitSettingDate = limitSettingDate;
    }
}
