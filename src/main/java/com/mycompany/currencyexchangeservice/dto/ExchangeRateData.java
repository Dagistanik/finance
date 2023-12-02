package com.mycompany.currencyexchangeservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeRateData {

    @JsonProperty("rate")
    private BigDecimal rate;

    @JsonProperty("previous_close")
    private BigDecimal previousClose;
}