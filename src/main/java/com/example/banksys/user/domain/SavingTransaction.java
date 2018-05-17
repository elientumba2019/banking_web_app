package com.example.banksys.user.domain;

import java.math.BigDecimal;
import java.util.Date;

public class SavingTransaction {



    private Long id;
    private Date date;
    private String desciption;
    private String type;
    private String status;
    private double amount;
    private BigDecimal availableBalance;

    private SavingAccount savingAccount;


    public SavingTransaction(){}


    public SavingTransaction(Date date,
                             String desciption,
                             String type,
                             String status,
                             double amount,
                             BigDecimal availableBalance,
                             SavingAccount savingAccount) {
        this.date = date;
        this.desciption = desciption;
        this.type = type;
        this.status = status;
        this.amount = amount;
        this.availableBalance = availableBalance;
        this.savingAccount = savingAccount;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public BigDecimal getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(BigDecimal availableBalance) {
        this.availableBalance = availableBalance;
    }

    public SavingAccount getSavingAccount() {
        return savingAccount;
    }

    public void setSavingAccount(SavingAccount savingAccount) {
        this.savingAccount = savingAccount;
    }
}
