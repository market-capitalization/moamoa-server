package com.shinhansec.marketcapitalization.transaction.controller;

import com.shinhansec.marketcapitalization.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;
}
