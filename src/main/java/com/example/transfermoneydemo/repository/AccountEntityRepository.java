package com.example.transfermoneydemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.transfermoneydemo.model.entity.AccountEntity;

@Repository
public interface AccountEntityRepository extends JpaRepository<AccountEntity, Long> {

}
