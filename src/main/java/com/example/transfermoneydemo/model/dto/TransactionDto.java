package com.example.transfermoneydemo.model.dto;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.example.transfermoneydemo.model.entity.AccountEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDto {

  private Double amount;

  private String currency;

  private Long sourceAccountId;

  private Long targetAccountId;
}
