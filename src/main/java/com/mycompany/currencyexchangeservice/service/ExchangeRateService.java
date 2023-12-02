package com.mycompany.currencyexchangeservice.service;

import com.mycompany.currencyexchangeservice.client.CurrencyExchangeClient;
import com.mycompany.currencyexchangeservice.dto.ExchangeRateData;
import com.mycompany.currencyexchangeservice.model.ExchangeRate;
import com.mycompany.currencyexchangeservice.repository.ExchangeRateRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ExchangeRateService {

    private final ExchangeRateRepository exchangeRateRepository;
    private final CurrencyExchangeClient currencyExchangeClient;

    public ExchangeRateService(ExchangeRateRepository exchangeRateRepository,
                               CurrencyExchangeClient currencyExchangeClient) {
        this.exchangeRateRepository = exchangeRateRepository;
        this.currencyExchangeClient = currencyExchangeClient;
    }

    public Optional<ExchangeRate> getExchangeRate(String currencyPair, LocalDate date) {
        return exchangeRateRepository.findByCurrencyPairAndDate(currencyPair, date);
    }

    public Optional<ExchangeRate> getLastAvailableExchangeRate(String currencyPair, LocalDate date) {
        return exchangeRateRepository.findTopByCurrencyPairAndDateBeforeOrderByDateDesc(currencyPair, date);
    }

    public void updateExchangeRate(String baseCurrency, String targetCurrency) {
        ExchangeRateData exchangeRateData = currencyExchangeClient.getExchangeRate(baseCurrency, targetCurrency);

        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setCurrencyPair(baseCurrency + "/" + targetCurrency);
        exchangeRate.setDate(LocalDate.now());
        exchangeRate.setRate(exchangeRateData.getRate());
        exchangeRate.setPreviousClose(exchangeRateData.getPreviousClose());

        exchangeRateRepository.save(exchangeRate);
    }

}