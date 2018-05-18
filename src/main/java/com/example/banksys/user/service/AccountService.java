package com.example.banksys.user.service;

import com.example.banksys.user.domain.PrimaryAccount;
import com.example.banksys.user.domain.SavingAccount;


public interface AccountService {

    PrimaryAccount  createPrimaryAccount();
    SavingAccount createSavingAccount();
}
