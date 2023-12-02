package com.mycompany.currencyexchangeservice.client;

import com.mycompany.currencyexchangeservice.dto.ExchangeRateData;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyExchangeClientImpl implements CurrencyExchangeClient {

    private final RestTemplate restTemplate;

    public CurrencyExchangeClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ExchangeRateData getExchangeRate(String baseCurrency, String targetCurrency) {
        String url = "twelvedata.com" + "base=" + baseCurrency + "&symbols=" + targetCurrency;
        return restTemplate.getForObject(url, ExchangeRateData.class);
    }
}
