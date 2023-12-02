package com.mycompany.currencyexchangeservice.client;

import com.mycompany.currencyexchangeservice.dto.ExchangeRateData;

public interface CurrencyExchangeClient {
    ExchangeRateData getExchangeRate(String baseCurrency, String targetCurrency);
}
