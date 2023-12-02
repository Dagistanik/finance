package com.mycompany.currencyexchangeservice.service;

import com.mycompany.currencyexchangeservice.enums.ExpenseCategory;
import com.mycompany.currencyexchangeservice.model.SpendingLimit;
import com.mycompany.currencyexchangeservice.repository.SpendingLimitRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Setter
@Service
public class SpendingLimitService {

    private final SpendingLimitRepository spendingLimitRepository;

    @Autowired
    public SpendingLimitService(SpendingLimitRepository spendingLimitRepository) {
        this.spendingLimitRepository = spendingLimitRepository;
    }

    public void setNewLimit(BigDecimal limitAmount, ExpenseCategory expenseCategory, LocalDate settingDate) {
        SpendingLimit newLimit = new SpendingLimit();
        newLimit.setLimitAmount(limitAmount);
        newLimit.setExpenseCategory(expenseCategory);
        newLimit.setLimitSettingDate(settingDate);
        spendingLimitRepository.save(newLimit);
    }

    public List<SpendingLimit> getLimitsForCategory(ExpenseCategory expenseCategory) {
        return spendingLimitRepository.findByExpenseCategory(expenseCategory);
    }

    public SpendingLimit setSpendingLimit(SpendingLimit spendingLimit) {
        if (spendingLimit.getLimitSettingDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Limit setting date cannot be in the future.");
        }

        if (spendingLimit.getLimitAmount() != null && spendingLimit.getLimitAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Limit amount must be greater than zero.");
        }

        if (spendingLimit.getExpenseCategory() == null) {
            throw new IllegalArgumentException("Expense category must be set.");
        }

        return spendingLimitRepository.save(spendingLimit);
    }
}
