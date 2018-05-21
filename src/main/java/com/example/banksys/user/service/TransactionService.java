package com.example.banksys.user.service;

import com.example.banksys.user.domain.PrimaryTransaction;
import com.example.banksys.user.domain.SavingTransaction;

import java.util.List;

public interface TransactionService {

    List<PrimaryTransaction> findPrimaryTransactionList(String username);
    List<SavingTransaction> findSavingTransactionList(String username);
    void savePrimaryDepositTransaction(PrimaryTransaction pt);
    void saveSavingDepositTransaction(SavingTransaction st);
    void savePrimaryWithdrawTransaction(PrimaryTransaction pt);
    void saveSavingWithdrawTransaction(SavingTransaction st);

}
