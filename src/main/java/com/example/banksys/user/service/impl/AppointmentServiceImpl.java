package com.example.banksys.user.service.impl;

import com.example.banksys.user.dao.AppointmentDao;
import com.example.banksys.user.domain.Appointment;
import com.example.banksys.user.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService {


    private AppointmentDao appointmentDao;


    @Autowired
    public void setAppointmentDao(AppointmentDao appointmentDao) {
        this.appointmentDao = appointmentDao;
    }


    @Override
    public void createAppointment(Appointment appointment) {
        appointmentDao.save(appointment);
    }

    @Override
    public List<Appointment> findAll() {
        return appointmentDao.findAll();
    }

    @Override
    public Appointment findAppointment(Long id) {
        return appointmentDao.findById(id).get();
    }

    @Override
    public void confirmAppointment(Long id) {
        Appointment appointment = findAppointment(id);
        appointment.setConfirmed(true);
        appointmentDao.save(appointment);
    }


}







