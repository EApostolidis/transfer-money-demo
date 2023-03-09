package com.example.transfermoneydemo.model.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "transaction")
public class TransactionEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Double amount;

  private String currency;
  @ManyToOne(fetch = FetchType.EAGER)
  private AccountEntity sourceAccountId;
  @ManyToOne(fetch = FetchType.EAGER)
  private AccountEntity targetAccountId;
}
