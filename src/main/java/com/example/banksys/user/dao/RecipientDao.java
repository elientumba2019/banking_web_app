package com.example.banksys.user.dao;

import com.example.banksys.user.domain.Recipient;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecipientDao extends CrudRepository<Recipient, String> {

    List<Recipient> findAll();
    Recipient findByName(String name);
    void deleteByName(String name);
}
