package com.ridivi.coraMiddlewere.model.service;

import javax.transaction.Transactional;

import com.ridivi.coraMiddlewere.model.repository.LogOperationRepository;
import com.ridivi.coraMiddlewere.model.entity.LogOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class LogOperationService {

    @Autowired
    private LogOperationRepository repo; 

    public void save(LogOperation pData){
        repo.save(pData);
    }
    
}
