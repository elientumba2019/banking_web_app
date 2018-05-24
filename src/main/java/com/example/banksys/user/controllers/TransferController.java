package com.example.banksys.user.controllers;


import com.example.banksys.user.domain.PrimaryAccount;
import com.example.banksys.user.domain.SavingAccount;
import com.example.banksys.user.domain.User;
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

@Controller
@RequestMapping("/transfer")
public class TransferController {


    private TransactionService transactionService;
    private UserService userService;

    private static final String TRANSFER_FROM = "transferFrom";
    private static final String TRANSFER_TO = "transferTo";
    private static final String AMOUNT = "amount";


    @Autowired
    public void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }



    @GetMapping("/betweenAccounts")
    public String betweenAccounts(Model model){
        model.addAttribute(TRANSFER_FROM , "");
        model.addAttribute(TRANSFER_TO, "");
        model.addAttribute(AMOUNT, "");

        return "betweenAccounts";
    }




    @PostMapping("/betweenAccounts")
    public String betweenAccountsPost(
            @ModelAttribute(TRANSFER_FROM) String from,
            @ModelAttribute(TRANSFER_TO) String to,
            @ModelAttribute(AMOUNT) String amount,
            Principal principal
    ) throws Exception{

        User user = userService.findByUsername(principal.getName());
        PrimaryAccount primaryAccount = user.getPrimaryAccount();
        SavingAccount savingAccount = user.getSavingAccount();
        transactionService.betweenAccountsTransfer(from, to, amount, primaryAccount, savingAccount);

        return "redirect:/userFront";
    }
}











