package com.example.banksys.user.dao;

import com.example.banksys.user.domain.SavingTransaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SavingTransactionDao extends CrudRepository<SavingTransaction , Long> {
    List<SavingTransaction> findAll();
}
