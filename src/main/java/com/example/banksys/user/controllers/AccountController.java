package com.example.banksys.user.controllers;

import com.example.banksys.user.domain.*;
import com.example.banksys.user.service.AccountService;
import com.example.banksys.user.service.TransactionService;
import com.example.banksys.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;


/**
 * Account controller class
 */
@Controller
@RequestMapping("/account")
public class AccountController {




    private UserService userService;
    private AccountService accountService;
    private TransactionService transactionService;
    private static final String PRIMARY_ACCOUNT = "primaryAccount";
    private static final String SAVINGS_ACCOUNT = "savingsAccount";
    private static final String ACCOUNT_TYPE = "accountType";
    private static final String AMOUNT = "amount";
    private static final String PRIMARY_TRANS_LIST = "primaryTransactionList";
    private static final String SAVINGS_TRANS_LIST = "savingsTransactionList";





    @Autowired
    public AccountController(UserService userService , AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }



    @Autowired
    public void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }



    @GetMapping("/primaryAccount")
    public String primaryAccount(Principal principal , Model model){

        List<PrimaryTransaction> primaryTransactionList =
                transactionService.findPrimaryTransactionList(principal.getName());


        User user = userService.findByUsername(principal.getName());
        PrimaryAccount primaryAccount = user.getPrimaryAccount();
        model.addAttribute(PRIMARY_ACCOUNT , primaryAccount);
        model.addAttribute(PRIMARY_TRANS_LIST, primaryTransactionList);

        return  "primaryAccount";
    }






    @GetMapping("/savingsAccount")
    public String savingAccount(Principal principal , Model model){

        List<SavingTransaction> savingTransactions = transactionService.findSavingTransactionList(principal.getName());

        User user = userService.findByUsername(principal.getName());
        SavingAccount savingAccount = user.getSavingAccount();
        model.addAttribute(SAVINGS_ACCOUNT , savingAccount);
        model.addAttribute(SAVINGS_TRANS_LIST , savingTransactions);

        return "savingsAccount";
    }





    @GetMapping("/deposit")
    public String deposit(Model model){
        model.addAttribute(ACCOUNT_TYPE , "");
        model.addAttribute(AMOUNT , "");

        return "deposit";
    }




    @PostMapping("/deposit")
    public String depositPost(@ModelAttribute(AMOUNT) String amount,
                              @ModelAttribute(ACCOUNT_TYPE) String accountType,
                              Principal principal
                              ){

        accountService.deposit(accountType , Double.parseDouble(amount) , principal);
        return "redirect:/userFront";
    }




    @GetMapping("/withdraw")
    public String withdraw(Model model){
        model.addAttribute(ACCOUNT_TYPE , "");
        model.addAttribute(AMOUNT , "");
        return "withdraw";
    }




    @PostMapping("/withdraw")
    public String withdrawPost(@ModelAttribute(ACCOUNT_TYPE) String accountType,
                               @ModelAttribute(AMOUNT) String amount,
                               Principal principal){


        accountService.withdraw(accountType ,
                Double.parseDouble(amount),
                principal);

        return "redirect:/userFront";
    }
}









