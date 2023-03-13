package com.example.transfermoneydemo.service;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.transfermoneydemo.model.dto.AccountDto;
import com.example.transfermoneydemo.model.entity.AccountEntity;
import com.example.transfermoneydemo.repository.AccountEntityRepository;

/**
 * Handles the account business logic.
 */
@Service
public class AccountService {

  private final AccountEntityRepository accountEntityRepository;

  public AccountService(AccountEntityRepository accountEntityRepository) {
    this.accountEntityRepository = accountEntityRepository;
  }

  /**
   * Fetches all the accounts transformed to the corresponding dto.
   * @return {@link List<AccountDto>}
   */
  public List<AccountDto> fetchAccounts() {
    return accountEntityRepository.findAll().stream()
        .map(account -> AccountDto.builder()
            .balance(account.getBalance())
            .currency(account.getCurrency())
            .build())
        .collect(Collectors.toList());
  }

  /**
   * Created the account entity.
   * @param accountDto {@link AccountDto}
   * @return {@link AccountEntity}
   */
  public AccountEntity createAccount(AccountDto accountDto) {
    AccountEntity accountEntity = new AccountEntity();
    accountEntity.setCreatedAt(LocalDateTime.now());
    accountEntity.setBalance(accountDto.getBalance());
    accountEntity.setCurrency(accountDto.getCurrency());
    checkAccountBalance(accountEntity.getBalance());
    return accountEntityRepository.save(accountEntity);
  }

  /**
   * Fetches the account with the id given as input
   * @param id the account id
   * @return {@link AccountEntity}
   */
  public AccountEntity getAccountById(Long id) {
    Optional<AccountEntity> accountEntity = accountEntityRepository.findById(id);
    if (accountEntity.isEmpty()) {
      final String errorMessage = MessageFormat.format("There is no account with id: {0}", id);
      throw new RuntimeException(errorMessage);
    }
    return accountEntity.get();
  }

  /**
   * Updates the account data making some validations
   * @param accountEntity {@link AccountEntity}
   * @return
   */
  public AccountEntity updateAccount(AccountEntity accountEntity) {
    checkAccountBalance(accountEntity.getBalance());
    return accountEntityRepository.save(accountEntity);
  }

  private void checkAccountBalance(Double balance) {
    if (balance < 0) {
      throw new RuntimeException("Balance cannot be negative.");
    }
  }
}
