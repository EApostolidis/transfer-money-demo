package com.example.transfermoneydemo.model.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDto {

  @NotNull
  private Double amount;
  @NotNull
  private String currency;
  @NotNull
  private Long sourceAccountId;
  @NotNull
  private Long targetAccountId;
}
