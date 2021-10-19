package com.ridivi.coraMiddlewere.model.service;


import com.ridivi.coraMiddlewere.model.entity.Responce;
import com.ridivi.coraMiddlewere.model.entity.Message;


public interface IMiddlewareService {

    public Responce ReceivedMessage(String rest);
    
    public Responce SendMessage(Message rest);
    
}
