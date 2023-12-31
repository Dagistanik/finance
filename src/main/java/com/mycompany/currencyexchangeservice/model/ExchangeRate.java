package com.mycompany.currencyexchangeservice.model;

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
@Table(name = "exchange_rates")
public class ExchangeRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "rate")
    private BigDecimal rate;

    @Column(name = "previous_close")
    private BigDecimal previousClose;

    @Column(name = "currency_pair")
    private String currencyPair;
}