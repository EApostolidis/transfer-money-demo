package com.example.transfermoneydemo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.transfermoneydemo.model.dto.AccountDto;
import com.example.transfermoneydemo.model.entity.AccountEntity;

public interface AccountApi {

  ResponseEntity<List<AccountDto>> fetchAll();
  ResponseEntity<AccountEntity> createAccount(AccountDto accountDto);

}
