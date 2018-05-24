package com.example.banksys.user.controllers;


import com.example.banksys.user.domain.PrimaryAccount;
import com.example.banksys.user.domain.Recipient;
import com.example.banksys.user.domain.SavingAccount;
import com.example.banksys.user.domain.User;
import com.example.banksys.user.service.TransactionService;
import com.example.banksys.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/transfer")
public class TransferController {


    private TransactionService transactionService;
    private UserService userService;

    private static final String TRANSFER_FROM = "transferFrom";
    private static final String TRANSFER_TO = "transferTo";
    private static final String AMOUNT = "amount";
    private static final String RECIPIENT_LIST = "recipientList";
    private static final String RECIPIENT = "recipient";
    private static final String RECIPIENT_NAME = "recipientName";
    private static final String ACCOUNT_TYPE = "accountType";


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




    @GetMapping("/recipient")
    public String recipient(Model model, Principal principal){
        List<Recipient> recipients = transactionService.findRecipientList(principal);
        Recipient recipient = new Recipient();

        model.addAttribute(RECIPIENT_LIST, recipients);
        model.addAttribute(RECIPIENT, recipient);

        return "recipient";
    }




    @PostMapping("/recipient/save")
    public String recipientPost(
            @ModelAttribute(RECIPIENT)
                    Recipient recipient,
            Principal principal){

        User user = userService.findByUsername(principal.getName());
        recipient.setUser(user);
        transactionService.saveRecipient(recipient);

        return "redirect:/transfer/recipient";
    }





    @GetMapping("/recipient/edit")
    public String recipientEdit(
            @RequestParam(value = RECIPIENT_NAME) String recipientName,
            Model model,
            Principal principal
    ){


        Recipient recipient = transactionService.findRecipientByName(recipientName);
        List<Recipient> recipients = transactionService.findRecipientList(principal);

        model.addAttribute(RECIPIENT_LIST, recipients);
        model.addAttribute(RECIPIENT, recipient);

        return "recipient";
    }





    @GetMapping("/recipient/delete")
    @Transactional
    public String recipientDelete(
            @RequestParam(RECIPIENT_NAME) String recipientName,
            Model model,
            Principal principal){

        transactionService.deleteRecipient(recipientName);
        List<Recipient> recipientList = transactionService.findRecipientList(principal);

        Recipient recipient = new Recipient();
        model.addAttribute(RECIPIENT, recipient);
        model.addAttribute(RECIPIENT_LIST , recipientList);

        return "recipient";
    }




    @GetMapping("/toSomeoneElse")
    public String toSomeoneElse(Model model , Principal principal){
        List<Recipient> recipients = transactionService.findRecipientList(principal);
        model.addAttribute(RECIPIENT_LIST, recipients);
        model.addAttribute(ACCOUNT_TYPE , "");
        return "toSomeoneElse";
    }




    @PostMapping("/toSomeoneElse")
    public String toSomeomeElsePost(
            @ModelAttribute(RECIPIENT_NAME) String recipientName,
            @ModelAttribute(ACCOUNT_TYPE) String accountType,
            @ModelAttribute(AMOUNT) String amount,
            Principal principal
    ){


        User user = userService.findByUsername(principal.getName());
        Recipient recipient = transactionService.findRecipientByName(recipientName);
        transactionService.toSomeoneElseTransfer(recipient, accountType, amount, user.getPrimaryAccount(), user.getSavingAccount());


        return "redirect:/userFront";
    }
}











