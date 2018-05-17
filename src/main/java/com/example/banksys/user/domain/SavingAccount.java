package com.example.banksys.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * saving trans entity
 */
@Entity
public class SavingAccount {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int accountNumber;
    private BigDecimal accountBalance;


    @OneToMany(mappedBy = "savingAccount",
    cascade = CascadeType.ALL,
    fetch = FetchType.LAZY)
    @JsonIgnore
    private List<SavingTransaction> savingTransactionList;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }

    public List<SavingTransaction> getSavingTransactionList() {
        return savingTransactionList;
    }

    public void setSavingTransactionList(List<SavingTransaction> savingTransactionList) {
        this.savingTransactionList = savingTransactionList;
    }
}
