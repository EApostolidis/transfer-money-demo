package com.example.transfermoneydemo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.transfermoneydemo.model.dto.TransactionDto;
import com.example.transfermoneydemo.service.TransactionService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/transactions", produces = APPLICATION_JSON_VALUE)
public class TransactionController implements TransactionApi {

  private final TransactionService transactionService;

  public TransactionController(TransactionService transactionService) {
    this.transactionService = transactionService;
  }

  /**
   * Creates transaction providing the correct data
   * @param transactionDto {@link TransactionDto}
   * @return {@link ResponseEntity<TransactionDto>}
   */
  @Override
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<TransactionDto> createTransaction(@Valid @RequestBody TransactionDto transactionDto) {
    TransactionDto response = transactionService.createTransaction(transactionDto);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
  }

  /**
   * Retrieves all the transactions
   * @return {@link ResponseEntity<List<TransactionDto>>}
   */
  @Override
  @GetMapping
  public ResponseEntity<List<TransactionDto>> fetchTransactions() {
    List<TransactionDto> response = transactionService.fetchTransactions();
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }
}
