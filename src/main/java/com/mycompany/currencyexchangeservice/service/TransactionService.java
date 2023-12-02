package com.mycompany.currencyexchangeservice.service;

import com.mycompany.currencyexchangeservice.model.SpendingLimit;
import com.mycompany.currencyexchangeservice.model.Transaction;
import com.mycompany.currencyexchangeservice.repository.SpendingLimitRepository;
import com.mycompany.currencyexchangeservice.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final ExchangeRateService exchangeRateService;
    private final SpendingLimitRepository spendingLimitRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository,
                              ExchangeRateService exchangeRateService,
                              SpendingLimitRepository spendingLimitRepository) {
        this.transactionRepository = transactionRepository;
        this.exchangeRateService = exchangeRateService;
        this.spendingLimitRepository = spendingLimitRepository;
    }

    @Transactional
    public Transaction recordTransaction(Transaction inputTransaction) {
        BigDecimal amountInUsd = convertCurrencyToUsd(inputTransaction.getAmount(), inputTransaction.getCurrency(), inputTransaction.getTransactionDate());

        Transaction transaction = new Transaction();
        transaction.setAmount(amountInUsd);
        transaction.setCurrency("USD");
        transaction.setTransactionDate(inputTransaction.getTransactionDate());

        checkAndMarkLimitExceeded(transaction);

        return transactionRepository.save(transaction);
    }

    public List<Transaction> getTransactionsExceedingLimits() {
        return transactionRepository.findByLimitExceeded(true);
    }

    private BigDecimal convertCurrencyToUsd(BigDecimal amount, String currency, LocalDate transactionDate) {
        BigDecimal rate = exchangeRateService.getExchangeRate(currency, transactionDate)
                .orElseGet(() -> exchangeRateService.getLastAvailableExchangeRate(currency, transactionDate)
                        .orElseThrow(() -> new IllegalArgumentException("No exchange rate available.")))
                .getRate();

        return amount.divide(rate, BigDecimal.ROUND_HALF_EVEN);
    }

    private void checkAndMarkLimitExceeded(Transaction transaction) {
        LocalDate transactionDate = transaction.getTransactionDate();
        BigDecimal amountInUsd = transaction.getAmount();

        List<SpendingLimit> limits = spendingLimitRepository.findAll();
        BigDecimal totalSpent = transactionRepository.sumAllTransactionsUntil(transactionDate.minusMonths(1), transactionDate);

        for (SpendingLimit limit : limits) {
            if (totalSpent.add(amountInUsd).compareTo(limit.getLimitAmount()) > 0) {
                transaction.setLimitExceeded(true);
                break;
            }
        }
    }

}
