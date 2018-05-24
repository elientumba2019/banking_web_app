package com.example.banksys.user.controllers;


import com.example.banksys.user.domain.Appointment;
import com.example.banksys.user.domain.User;
import com.example.banksys.user.service.AppointmentService;
import com.example.banksys.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {

    private AppointmentService appointmentService;
    private UserService userService;
    private static final String APPOINTMENT = "appointment";
    private static final String DATE_STRING = "dateString";



    @Autowired
    public void setAppointmentService(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }




    @GetMapping("/create")
    public String createAppointment(Model model){
        Appointment appointment = new Appointment();
        model.addAttribute(APPOINTMENT, appointment);
        model.addAttribute(DATE_STRING , "");

        return "appointment";
    }




    @PostMapping("/create")
    public String createAppointmentPost(
            @ModelAttribute(APPOINTMENT)Appointment appointment,
            @ModelAttribute(DATE_STRING) String dateString,
            Model model,
            Principal principal
            ) throws ParseException {


        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date d1 = format.parse(dateString);
        appointment.setDate(d1);

        User user = userService.findByUsername(principal.getName());
        appointment.setUser(user);


        appointmentService.createAppointment(appointment);
        return "redirect:/userFront";
    }
}










