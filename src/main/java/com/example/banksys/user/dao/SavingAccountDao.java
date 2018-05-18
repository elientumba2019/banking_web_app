package com.example.banksys.user.dao;

import com.example.banksys.user.domain.SavingAccount;
import org.springframework.data.repository.CrudRepository;

public interface SavingAccountDao extends CrudRepository<SavingAccount , Long> {
    SavingAccount findByAccountNumber (int accountNumber);
}
