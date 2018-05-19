package com.example.banksys.user.controllers;

import com.example.banksys.user.domain.PrimaryAccount;
import com.example.banksys.user.domain.SavingAccount;
import com.example.banksys.user.domain.User;
import com.example.banksys.user.service.AccountService;
import com.example.banksys.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;


/**
 * Account controller class
 */
@Controller
@RequestMapping("/account")
public class AccountController {




    private UserService userService;
    private AccountService accountService;
    private static final String PRIMARY_ACCOUNT = "primaryAccount";
    private static final String SAVINGS_ACCOUNT = "savingsAccount";
    private static final String ACCOUNT_TYPE = "accountType";
    private static final String AMOUNT = "amount";



    @Autowired
    public AccountController(UserService userService , AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }




    @GetMapping("/primaryAccount")
    public String primaryAccount(Principal principal , Model model){

        User user = userService.findByUsername(principal.getName());
        PrimaryAccount primaryAccount = user.getPrimaryAccount();
        model.addAttribute(PRIMARY_ACCOUNT , primaryAccount);

        return  "primaryAccount";
    }




    @GetMapping("/savingsAccount")
    public String savingAccount(Principal principal , Model model){

        User user = userService.findByUsername(principal.getName());
        SavingAccount savingAccount = user.getSavingAccount();
        model.addAttribute(SAVINGS_ACCOUNT , savingAccount);

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
}









