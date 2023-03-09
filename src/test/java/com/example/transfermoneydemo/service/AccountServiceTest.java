package com.example.transfermoneydemo.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.transfermoneydemo.model.dto.AccountDto;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class AccountServiceTest {

  @Autowired
  private AccountService accountService;

  @Test
  void getAccountById_Test_unsuccessful() {
    var response = accountService.fetchAccounts();
    Throwable exception = assertThrows(RuntimeException.class, () -> accountService.getAccountById(300L));
    Assertions.assertEquals("There is no account with id: 300", exception.getMessage());
  }

  @Test
  void createAccount_Test_successful() {
    AccountDto request = AccountDto.builder()
        .currency("EUR")
        .balance(1000.00)
        .build();

    var response = accountService.createAccount(request);
    Assertions.assertEquals(1000.00, response.getBalance());
    Assertions.assertEquals("EUR", response.getCurrency());
  }

  @Test
  void createAccount_Test_unsuccessful() {
    AccountDto request = AccountDto.builder()
        .currency("EUR")
        .balance(-100.00)
        .build();

    Throwable exception = assertThrows(RuntimeException.class, () -> accountService.createAccount(request));
    Assertions.assertEquals("Balance cannot be negative.", exception.getMessage());
  }

}