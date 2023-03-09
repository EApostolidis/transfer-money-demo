package com.example.transfermoneydemo.model.controller;

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

import com.example.transfermoneydemo.model.dto.AccountDto;
import com.example.transfermoneydemo.model.entity.AccountEntity;
import com.example.transfermoneydemo.service.AccountService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/accounts", produces = APPLICATION_JSON_VALUE)
public class AccountController implements AccountApi {

  private final AccountService accountService;

  public AccountController(AccountService accountService) {
    this.accountService = accountService;
  }

  @Override
  @GetMapping
  public ResponseEntity<List<AccountDto>> fetchAll() {
    List<AccountDto> response = accountService.fetchAccounts();
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @Override
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<AccountEntity> createAccount(@Valid @RequestBody AccountDto accountDto) {
    AccountEntity response = accountService.createAccount(accountDto);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
  }
}
