package com.example.banksys.user.service;

import com.example.banksys.user.domain.*;

import java.security.Principal;
import java.util.List;

public interface TransactionService {

    List<PrimaryTransaction> findPrimaryTransactionList(String username);
    List<SavingTransaction> findSavingTransactionList(String username);
    void savePrimaryDepositTransaction(PrimaryTransaction pt);
    void saveSavingDepositTransaction(SavingTransaction st);
    void savePrimaryWithdrawTransaction(PrimaryTransaction pt);
    void saveSavingWithdrawTransaction(SavingTransaction st);

    void betweenAccountsTransfer(String from, String to, String amount, PrimaryAccount primaryAccount, SavingAccount savingAccount) throws Exception;

    List<Recipient> findRecipientList(Principal principal);

    void saveRecipient(Recipient recipient);

    Recipient findRecipientByName(String recipientName);

    void deleteRecipient(String recipientName);

    void toSomeoneElseTransfer(Recipient recipient, String accountType, String amount, PrimaryAccount primaryAccount, SavingAccount savingAccount);
}
