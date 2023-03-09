package com.example.transfermoneydemo.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.transfermoneydemo.model.dto.TransactionDto;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class TransactionServiceTest {

  @Autowired
  TransactionService transactionService;

  @Autowired
  AccountService accountService;

  @Test
  void successful_createTransaction() {
    TransactionDto request = TransactionDto.builder()
        .sourceAccountId(1L)
        .targetAccountId(2L)
        .currency("EUR")
        .amount(400.00)
        .build();

    var respose = transactionService.createTransaction(request);
    var sourceAccount = accountService.getAccountById(1L);
    var targetAccount = accountService.getAccountById(2L);
    Assertions.assertEquals(1L, respose.getSourceAccountId());
    Assertions.assertEquals(2L, respose.getTargetAccountId());
    Assertions.assertEquals(400.00, respose.getAmount());
    Assertions.assertEquals("EUR", respose.getCurrency());
    Assertions.assertEquals(600.00, sourceAccount.getBalance());
    Assertions.assertEquals(1400.00, targetAccount.getBalance());
  }

  @Test
  void unsuccessful_ac2_createTransaction() {
    TransactionDto request = TransactionDto.builder()
        .sourceAccountId(1L)
        .targetAccountId(2L)
        .currency("EUR")
        .amount(1005.00)
        .build();

    Throwable exception = assertThrows(RuntimeException.class, () -> transactionService.createTransaction(request));
    Assertions.assertEquals("There is not available balance for the transaction", exception.getMessage());
  }

  @Test
  void unsuccessful_ac3_createTransaction() {
    TransactionDto request = TransactionDto.builder()
        .sourceAccountId(1L)
        .targetAccountId(1L)
        .currency("EUR")
        .amount(500.00)
        .build();

    Throwable exception = assertThrows(RuntimeException.class, () -> transactionService.createTransaction(request));
    Assertions.assertEquals("Target account id and source account id cannot be the same", exception.getMessage());
  }

  @Test
  void unsuccessful_ac4_createTransaction() {
    TransactionDto request = TransactionDto.builder()
        .sourceAccountId(1L)
        .targetAccountId(4L)
        .currency("EUR")
        .amount(500.00)
        .build();

    Throwable exception = assertThrows(RuntimeException.class, () -> transactionService.createTransaction(request));
    Assertions.assertEquals("There is no account with id: 4", exception.getMessage());
  }
}