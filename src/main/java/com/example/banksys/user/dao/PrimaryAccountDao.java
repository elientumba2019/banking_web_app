package com.example.banksys.user.dao;

import com.example.banksys.user.domain.PrimaryAccount;
import org.springframework.data.repository.CrudRepository;

public interface PrimaryAccountDao extends CrudRepository<PrimaryAccount , Long> {
    PrimaryAccount findByAccountNumber(int accountNumber);
}
