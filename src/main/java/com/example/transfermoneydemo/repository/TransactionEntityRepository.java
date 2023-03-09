package com.example.transfermoneydemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.transfermoneydemo.model.entity.TransactionEntity;

public interface TransactionEntityRepository extends JpaRepository<TransactionEntity, Long> {

}
