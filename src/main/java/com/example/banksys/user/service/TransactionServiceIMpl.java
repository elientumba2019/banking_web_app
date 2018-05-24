package com.example.banksys.user.service;


import com.example.banksys.user.dao.*;
import com.example.banksys.user.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TransactionServiceIMpl implements TransactionService {


    private UserService userService;
    private SavingTransactionDao savingTransactionDao;
    private PrimaryTransactionDao primaryTransactionDao;
    private PrimaryAccountDao primaryAccountDao;
    private SavingAccountDao savingAccountDao;
    private RecipientDao recipientDao;




    @Autowired
    public void setRecipientDao(RecipientDao recipientDao) {
        this.recipientDao = recipientDao;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @Autowired
    public void setSavingTransactionDao(SavingTransactionDao savingTransactionDao) {
        this.savingTransactionDao = savingTransactionDao;
    }

    @Autowired
    public void setPrimaryAccountDao(PrimaryAccountDao primaryAccountDao) {
        this.primaryAccountDao = primaryAccountDao;
    }

    @Autowired
    public void setSavingAccountDao(SavingAccountDao savingAccountDao) {
        this.savingAccountDao = savingAccountDao;
    }

    @Autowired
    public void setPrimaryTransactionDao(PrimaryTransactionDao primaryTransactionDao) {
        this.primaryTransactionDao = primaryTransactionDao;
    }




    @Override
    public List<PrimaryTransaction> findPrimaryTransactionList(String username){
        User user = userService.findByUsername(username);
        List<PrimaryTransaction> transactions = user.getPrimaryAccount().getPrimaryTransactions();
        return transactions;
    }




    @Override
    public List<SavingTransaction> findSavingTransactionList(String username){
        User user = userService.findByUsername(username);
        List<SavingTransaction> savingTransactions = user.getSavingAccount().getSavingTransactionList();
        return savingTransactions;
    }



    @Override
    public void savePrimaryDepositTransaction(PrimaryTransaction pt){
        primaryTransactionDao.save(pt);
    }



    @Override
    public void saveSavingDepositTransaction(SavingTransaction st){
        savingTransactionDao.save(st);
    }




    @Override
    public void savePrimaryWithdrawTransaction(PrimaryTransaction pt){
        primaryTransactionDao.save(pt);
    }






    @Override
    public void saveSavingWithdrawTransaction(SavingTransaction st){
        savingTransactionDao.save(st);
    }





    @Override
    public void betweenAccountsTransfer(String from, String to, String amount, PrimaryAccount primaryAccount, SavingAccount savingAccount) throws Exception {

        if(from.equalsIgnoreCase("Primary") && to.equalsIgnoreCase("Savings")){
            primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().subtract(new BigDecimal(amount)));
            savingAccount.setAccountBalance(savingAccount.getAccountBalance().add(new BigDecimal(amount)));
            primaryAccountDao.save(primaryAccount);
            savingAccountDao.save(savingAccount);

            Date date = new Date();
            PrimaryTransaction primaryTransaction = new PrimaryTransaction(
                    date,
                    "Between accounts transfer ",
                    "Transfer",
                    "Terminated",
                    Double.valueOf(amount),
                    primaryAccount.getAccountBalance(),
                    primaryAccount
            );
            primaryTransactionDao.save(primaryTransaction);
        }


        else if (from.equalsIgnoreCase("Savings") && to.equalsIgnoreCase("Primary")){
            savingAccount.setAccountBalance(savingAccount.getAccountBalance().subtract(new BigDecimal(amount)));
            primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().add(new BigDecimal(amount)));
            primaryAccountDao.save(primaryAccount);
            savingAccountDao.save(savingAccount);

            Date date = new Date();
            SavingTransaction savingTransaction = new SavingTransaction(
                   date,
                    "Transfer from saving to primary",
                    "Transfer",
                    "Terminated",
                    Double.valueOf(amount),
                    savingAccount.getAccountBalance(),
                    savingAccount
            );
            savingTransactionDao.save(savingTransaction);
        }

        else {
            throw new Exception("Invalid Transfer");
        }
    }






    @Override
    public List<Recipient> findRecipientList(Principal principal) {
        String username = principal.getName();
        return recipientDao.findAll().stream()
                .filter(recipient -> username.equals(recipient.getUser().getUsername()))
                .collect(Collectors.toList());
    }



    @Override
    public void saveRecipient(Recipient recipient) {
        recipientDao.save(recipient);
    }



    @Override
    public Recipient findRecipientByName(String recipientName) {
        return recipientDao.findByName(recipientName);
    }




    @Override
    public void deleteRecipient(String recipientName) {
        recipientDao.deleteByName(recipientName);
    }



    @Override
    public void toSomeoneElseTransfer(Recipient recipient, String accountType, String amount, PrimaryAccount primaryAccount, SavingAccount savingAccount) {
        if(accountType.equalsIgnoreCase("Primary")){
            primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().subtract(new BigDecimal(amount)));
            primaryAccountDao.save(primaryAccount);

            Date date = new Date();
            PrimaryTransaction primaryTransaction = new PrimaryTransaction(
                    date,
                    "Transfer to recipient : " + recipient.getName(),
                    "Transfer",
                    "Terminated",
                    Double.valueOf(amount),
                    primaryAccount.getAccountBalance(),
                    primaryAccount
            );
            primaryTransactionDao.save(primaryTransaction);
        }


        else if(accountType.equalsIgnoreCase("Savings")){
            savingAccount.setAccountBalance(savingAccount.getAccountBalance().subtract(new BigDecimal(amount)));
            savingAccountDao.save(savingAccount);

            Date date = new Date();
            SavingTransaction savingTransaction = new SavingTransaction(
                    date,
                    "Transfer from saving to :" + recipient.getName(),
                    "Transfer",
                    "Terminated",
                    Double.valueOf(amount),
                    savingAccount.getAccountBalance(),
                    savingAccount
            );
            savingTransactionDao.save(savingTransaction);
        }
    }
}
