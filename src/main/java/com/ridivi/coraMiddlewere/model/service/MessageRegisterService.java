package com.ridivi.coraMiddlewere.model.service;

import javax.transaction.Transactional;

import com.ridivi.coraMiddlewere.model.entity.MessageRegister;
import com.ridivi.coraMiddlewere.model.repository.MessageRegisterRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class MessageRegisterService {


    @Autowired
    private MessageRegisterRepository repo; 

    public void save(MessageRegister pData){
        repo.save(pData);
    }
    
}
