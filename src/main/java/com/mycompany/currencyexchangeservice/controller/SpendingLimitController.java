package com.mycompany.currencyexchangeservice.controller;

import com.mycompany.currencyexchangeservice.model.SpendingLimit;
import com.mycompany.currencyexchangeservice.service.SpendingLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/limits")
public class SpendingLimitController {

    private final SpendingLimitService spendingLimitService;

    @Autowired
    public SpendingLimitController(SpendingLimitService spendingLimitService) {
        this.spendingLimitService = spendingLimitService;
    }

    @PostMapping
    public ResponseEntity<SpendingLimit> setSpendingLimit(@RequestBody SpendingLimit spendingLimit) {
        SpendingLimit newLimit = spendingLimitService.setSpendingLimit(spendingLimit);
        return ResponseEntity.ok(newLimit);
    }
}