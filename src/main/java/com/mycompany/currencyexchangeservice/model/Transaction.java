package com.mycompany.currencyexchangeservice.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "currency")
    private String currency;

    @Column(name = "transaction_date")
    private LocalDate transactionDate;

    @Column(name = "limit_exceeded")
    private Boolean limitExceeded;



    // Конструкторы, геттеры и сеттеры
}
