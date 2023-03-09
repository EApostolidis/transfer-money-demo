package com.example.transfermoneydemo.model.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "account")
public class AccountEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Double balance;

  private String currency;

  private LocalDateTime createdAt;

  @OneToMany(mappedBy = "sourceAccountId", fetch = FetchType.EAGER)
  private Set<TransactionEntity> transactionsAsSource = new HashSet<>();

  @OneToMany(mappedBy = "targetAccountId", fetch = FetchType.EAGER)
  private Set<TransactionEntity> transactionsAsTarget = new HashSet<>();

}
