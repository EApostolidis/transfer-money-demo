package com.example.transfermoneydemo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.transfermoneydemo.model.dto.TransactionDto;
import com.example.transfermoneydemo.model.entity.AccountEntity;
import com.example.transfermoneydemo.model.entity.TransactionEntity;
import com.example.transfermoneydemo.repository.TransactionEntityRepository;

@Service
public class TransactionService {

  private final AccountService accountService;
  private final TransactionEntityRepository transactionEntityRepository;

  public TransactionService(AccountService accountService, TransactionEntityRepository transactionEntityRepository) {
    this.accountService = accountService;
    this.transactionEntityRepository = transactionEntityRepository;
  }

  public List<TransactionDto> fetchTransactions(){
    return transactionEntityRepository.findAll().stream()
        .map(transaction -> TransactionDto.builder()
            .currency(transaction.getCurrency())
            .amount(transaction.getAmount())
            .targetAccountId(transaction.getTargetAccountId().getId())
            .sourceAccountId(transaction.getSourceAccountId().getId())
            .build())
        .collect(Collectors.toList());
  }

  public TransactionDto createTransaction(TransactionDto transactionDto) {
    AccountEntity sourceAccount = accountService.getAccountById(transactionDto.getSourceAccountId());
    AccountEntity targetAccount = accountService.getAccountById(transactionDto.getTargetAccountId());
    checkTransactionAmount(sourceAccount.getBalance(), transactionDto.getAmount());
    checkAccounts(sourceAccount.getId(), targetAccount.getId());
    sourceAccount.setBalance(sourceAccount.getBalance() - transactionDto.getAmount());
    targetAccount.setBalance(targetAccount.getBalance() + transactionDto.getAmount());
    accountService.updateAccount(sourceAccount);
    accountService.updateAccount(targetAccount);
    TransactionEntity transactionEntity = new TransactionEntity();
    transactionEntity.setCurrency(transactionDto.getCurrency());
    transactionEntity.setAmount(transactionDto.getAmount());
    transactionEntity.setSourceAccountId(sourceAccount);
    transactionEntity.setTargetAccountId(targetAccount);
    TransactionEntity transaction = transactionEntityRepository.save(transactionEntity);
    return TransactionDto.builder()
        .amount(transaction.getAmount())
        .currency(transaction.getCurrency())
        .sourceAccountId(sourceAccount.getId())
        .targetAccountId(targetAccount.getId())
        .build();
  }

  private void checkTransactionAmount(Double sourceBalance, Double amount) {
    if (amount > sourceBalance) {
      throw new RuntimeException("There is not available balance for the transaction");
    }
  }

  private void checkAccounts(Long sourceAccountId,Long targetAccountId) {
    if(sourceAccountId.equals(targetAccountId)) {
      throw new RuntimeException("Target account id and source account id cannot be the same");
    }
  }
}
