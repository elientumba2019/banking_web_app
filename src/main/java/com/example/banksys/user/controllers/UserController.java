package com.example.banksys.user.controllers;


import com.example.banksys.user.domain.User;
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
@RequestMapping("/user")
public class UserController {


    private UserService userService;
    private static final String USER = "user";



    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }



    @GetMapping("/profile")
    public String profile(Principal principal, Model model){
        User user = userService.findByUsername(principal.getName());
        model.addAttribute(USER, user);
        return "profile";
    }




    @PostMapping("/profile")
    public String profilePost(
            @ModelAttribute(USER) User userx,
            Model model
            ){


        User user = userService.findByUsername(userx.getUsername());
        user.setUsername(userx.getUsername());
        user.setFirstName(userx.getFirstName());
        user.setLastName(userx.getLastName());
        user.setEmail(userx.getEmail());
        user.setPhone(userx.getPhone());

        model.addAttribute(USER, user);
        userService.saveUser(user);

        return "profile";
    }
}





