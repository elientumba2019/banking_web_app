package com.example.banksys.user.service;

import com.example.banksys.user.domain.Appointment;
import sun.rmi.runtime.Log;

import java.util.List;

public interface AppointmentService {
    void createAppointment(Appointment appointment);
    List<Appointment> findAll();
    Appointment findAppointment(Long id);
    void confirmAppointment(Long id);
}
