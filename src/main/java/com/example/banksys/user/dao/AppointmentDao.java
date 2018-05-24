package com.example.banksys.user.dao;

import com.example.banksys.user.domain.Appointment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AppointmentDao extends CrudRepository<Appointment, Long> {

    List<Appointment> findAll();
    Optional<Appointment> findById(Long id);

}
