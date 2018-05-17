package com.example.banksys.user.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * home controller class
 */

@Controller
public class HomeControllers {


    @GetMapping("/")
    public String home(){
        return "redirect:/index";
    }



    @GetMapping("/index")
    public String index(){
        return "index";
    }


}
