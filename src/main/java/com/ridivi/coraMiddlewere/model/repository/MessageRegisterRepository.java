package com.ridivi.coraMiddlewere.model.repository;

import com.ridivi.coraMiddlewere.model.entity.MessageRegister;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRegisterRepository extends JpaRepository <MessageRegister, Integer>{
    
}
