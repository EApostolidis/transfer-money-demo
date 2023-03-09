package com.example.transfermoneydemo.model.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.transfermoneydemo.model.dto.TransactionDto;

public interface TransactionApi {

  ResponseEntity<TransactionDto> createTransaction(TransactionDto transactionDto);
  ResponseEntity<List<TransactionDto>> fetchTransactions();
}
