package com.example.banksys.user.resources;


import com.example.banksys.user.domain.PrimaryTransaction;
import com.example.banksys.user.domain.SavingTransaction;
import com.example.banksys.user.domain.User;
import com.example.banksys.user.service.TransactionService;
import com.example.banksys.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@PreAuthorize("hasRole('ADMIN')")
public class UserResource {


    private TransactionService transactionService;
    private UserService userService;



    @Autowired
    public void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }



    @GetMapping("/user/all")
    public List<User> userList(){
        return userService.findUserList();
    }





    @GetMapping("/user/primary/transaction")
    public List<PrimaryTransaction> getPrimaryTransactionList(
            @RequestParam("username") String username
            ){
        return transactionService.findPrimaryTransactionList(username);
    }





    @GetMapping("/user/savings/transaction")
    public List<SavingTransaction> getSavingsTransactionList(
            @RequestParam("username") String username
    ){
        return transactionService.findSavingTransactionList(username);
    }




    @GetMapping("/user{username}/enable")
    public void enableUser(@PathVariable("username") String username){
        userService.enableUser(username);
    }



    @GetMapping("/user{username}/disable")
    public void disableUser(@PathVariable("username") String username){
        userService.disable(username);
    }

}






