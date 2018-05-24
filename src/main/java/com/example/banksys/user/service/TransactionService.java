package com.example.banksys.user.service;

import com.example.banksys.user.domain.PrimaryAccount;
import com.example.banksys.user.domain.PrimaryTransaction;
import com.example.banksys.user.domain.SavingAccount;
import com.example.banksys.user.domain.SavingTransaction;

import java.util.List;

public interface TransactionService {

    List<PrimaryTransaction> findPrimaryTransactionList(String username);
    List<SavingTransaction> findSavingTransactionList(String username);
    void savePrimaryDepositTransaction(PrimaryTransaction pt);
    void saveSavingDepositTransaction(SavingTransaction st);
    void savePrimaryWithdrawTransaction(PrimaryTransaction pt);
    void saveSavingWithdrawTransaction(SavingTransaction st);

    void betweenAccountsTransfer(String from, String to, String amount, PrimaryAccount primaryAccount, SavingAccount savingAccount) throws Exception;
}
