package com.example.banksys.user.controllers;
import com.example.banksys.user.domain.User;
import com.example.banksys.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


/**
 * home controller class
 */

@Controller
public class HomeControllers {

    private static final String USER_NAME = "user";
    private static final String EMAIL_EXIST = "emais exist";
    private static final String USERNAME_EXIST = "username exist";



    private UserService userService;



    @Autowired
    public HomeControllers(UserService userService) {
        this.userService = userService;
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

            //Set<UserRole> userRoles = new HashSet<>();
            //userRoles.add(new UserRole(user , roleDao.findByName("USER")));
            userService.saveUser(user);
        }


        return "redirect:/";
    }



}







