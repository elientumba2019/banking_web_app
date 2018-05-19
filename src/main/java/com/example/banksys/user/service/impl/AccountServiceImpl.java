package com.example.banksys.user.service.impl;

import com.example.banksys.user.dao.SavingAccountDao;
import com.example.banksys.user.domain.*;
import com.example.banksys.user.service.AccountService;
import com.example.banksys.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.banksys.user.dao.PrimaryAccountDao;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;


@Service
@Transactional
public class AccountServiceImpl implements AccountService {


    private PrimaryAccountDao primaryAccountDao;
    private SavingAccountDao savingAccountDao;
    private UserService userService;

    private static int nextAccountNumber = (int)(Math.random() * 1000000);


    @Autowired
    public AccountServiceImpl(PrimaryAccountDao primaryAccountDao, SavingAccountDao savingAccountDao) {

        this.primaryAccountDao = primaryAccountDao;
        this.savingAccountDao = savingAccountDao;
    }


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @Override
    public PrimaryAccount createPrimaryAccount() {
        PrimaryAccount primaryAccount = new PrimaryAccount();
        primaryAccount.setAccountBalance(new BigDecimal(0.0));
        primaryAccount.setAccountNumber(accountGen());
        primaryAccountDao.save(primaryAccount);

        return primaryAccountDao.findByAccountNumber(primaryAccount.getAccountNumber());
    }



    @Override
    public SavingAccount createSavingAccount() {
        SavingAccount savingAccount = new SavingAccount();
        savingAccount.setAccountBalance(new BigDecimal(0.0));
        savingAccount.setAccountNumber(accountGen());
        savingAccountDao.save(savingAccount);
        return savingAccountDao.findByAccountNumber(savingAccount.getAccountNumber());
    }




    @Override
    public void deposit(String accountType, double v, Principal principal) {

        User user = userService.findByUsername(principal.getName());

        if(accountType.equalsIgnoreCase("Primary")){
            PrimaryAccount primaryAccount = user.getPrimaryAccount();
            primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().add(new BigDecimal(v)));
            primaryAccountDao.save(primaryAccount);

            Date date = new Date();
            PrimaryTransaction pt = new PrimaryTransaction(date ,
                    "Deposit to primary Account",
                    "Account",
                    "Finished",
                    v,
                    primaryAccount.getAccountBalance(),
                    primaryAccount);
        }


        else if(accountType.equalsIgnoreCase("Saving")){
            SavingAccount savingAccount = user.getSavingAccount();
            savingAccount.setAccountBalance(savingAccount.getAccountBalance().add(new BigDecimal(v)));
            savingAccountDao.save(savingAccount);

            Date date = new Date();
            SavingTransaction savingTransaction = new SavingTransaction(date ,
                    "Deposit to savings account",
                    "Account",
                    "Finished",
                    v,
                    savingAccount.getAccountBalance(),
                    savingAccount);
        }
    }




    private int accountGen() {
        return ++nextAccountNumber;
    }

}
