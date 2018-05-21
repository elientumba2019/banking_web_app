package com.example.banksys.user.service;


import com.example.banksys.user.dao.PrimaryTransactionDao;
import com.example.banksys.user.dao.SavingTransactionDao;
import com.example.banksys.user.domain.PrimaryTransaction;
import com.example.banksys.user.domain.SavingTransaction;
import com.example.banksys.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TransactionServiceIMpl implements TransactionService {


    private UserService userService;
    private SavingTransactionDao savingTransactionDao;
    private PrimaryTransactionDao primaryTransactionDao;


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @Autowired
    public void setSavingTransactionDao(SavingTransactionDao savingTransactionDao) {
        this.savingTransactionDao = savingTransactionDao;
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
}
