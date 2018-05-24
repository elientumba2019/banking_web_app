package com.example.banksys.user.controllers;
import com.example.banksys.user.domain.PrimaryAccount;
import com.example.banksys.user.domain.SavingAccount;
import com.example.banksys.user.domain.User;
import com.example.banksys.user.domain.security.UserRole;
import com.example.banksys.user.service.RoleService;
import com.example.banksys.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;


/**
 * home controller class
 */

@Controller
public class HomeControllers {

    private static final String USER_NAME = "user";
    private static final String EMAIL_EXIST = "emailExists";
    private static final String USERNAME_EXIST = "usernameExists";
    private static final String PRIMARY_ACCOUNT = "primaryAccount";
    private static final String SAVINGS_ACCOUNT = "savingsAccount";

    private UserService userService;
    private RoleService roleService;



    @Autowired
    public HomeControllers(UserService userService , RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }



    @GetMapping("/")
    public String home(){
        return "redirect:/index";
    }




    @GetMapping("/index")
    public String index(){
        return "index";
    }





    @GetMapping("/signup")
    public String signup(Model model){
        User user = new User();
        model.addAttribute(USER_NAME , user);
        return "signup";
    }



    @PostMapping("/signup")
    public String signupPost(@ModelAttribute(USER_NAME) User user
            , Model model){


        System.out.println("Home controller : User : " + user.toString());


        if(userService.checkUserExist(user.getUsername() , user.getEmail())){
            if(userService.checkEmailExist(user.getEmail())){
                model.addAttribute(EMAIL_EXIST , true);
            }
            if(userService.checkUsernameExist(user.getUsername())){
                model.addAttribute(USERNAME_EXIST , true);
            }

            return  "signup";
        }
        else{

            Set<UserRole> userRoles = new HashSet<>();
            userRoles.add(new UserRole(user , roleService.findByName("ROLE_USER")));
            userService.createUser(user ,userRoles);
        }

        return "redirect:/";

    }






    @GetMapping("/userFront")
    public String userFront(Principal principal , Model model){

        User user = userService.findByUsername(principal.getName());
        PrimaryAccount primaryAccount = user.getPrimaryAccount();
        SavingAccount savingAccount = user.getSavingAccount();

        model.addAttribute(PRIMARY_ACCOUNT , primaryAccount);
        model.addAttribute(SAVINGS_ACCOUNT , savingAccount);

        return "userFront";
    }



}







