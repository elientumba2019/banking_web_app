package com.example.banksys.user.service;

import com.example.banksys.user.domain.PrimaryAccount;
import com.example.banksys.user.domain.SavingAccount;

import java.security.Principal;


public interface AccountService {

    PrimaryAccount  createPrimaryAccount();
    SavingAccount createSavingAccount();
    void deposit(String accountType, double v, Principal principal);
}
