package com.example.banksys.user.service.impl;

import com.example.banksys.user.dao.SavingAccountDao;
import com.example.banksys.user.domain.PrimaryAccount;
import com.example.banksys.user.domain.SavingAccount;
import com.example.banksys.user.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.banksys.user.dao.PrimaryAccountDao;

import javax.transaction.Transactional;
import java.math.BigDecimal;


@Service
@Transactional
public class AccountServiceImpl implements AccountService {


    private PrimaryAccountDao primaryAccountDao;
    private SavingAccountDao savingAccountDao;
    private static int nextAccountNumber = (int)(Math.random() * 1000000);


    @Autowired
    public AccountServiceImpl(PrimaryAccountDao primaryAccountDao, SavingAccountDao savingAccountDao) {
        this.primaryAccountDao = primaryAccountDao;
        this.savingAccountDao = savingAccountDao;
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




    private int accountGen() {
        return ++nextAccountNumber;
    }

}
