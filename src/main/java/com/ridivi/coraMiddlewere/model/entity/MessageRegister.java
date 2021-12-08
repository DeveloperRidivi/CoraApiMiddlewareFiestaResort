package com.ridivi.coraMiddlewere.model.entity;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MessageRegister")
public class MessageRegister {


    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer id;


    private String type;

    private String TextType;

    private String IdConversation;

    private String idSunshineUser;

    private String contend;

    private String subContend;

    private Date addDate;


    

    public MessageRegister() {

        this.addDate = new Date();
    }




    public MessageRegister(Integer id, String type, String textType, String idConversation, String idSunshineUser,
            String contend, String subContend, Date addDate) {
        this.id = id;
        this.type = type;
        TextType = textType;
        IdConversation = idConversation;
        this.idSunshineUser = idSunshineUser;
        this.contend = contend;
        this.subContend = subContend;
        this.addDate = addDate;
    }




    public Integer getId() {
        return id;
    }




    public void setId(Integer id) {
        this.id = id;
    }




    public String getType() {
        return type;
    }




    public void setType(String type) {
        this.type = type;
    }




    public String getTextType() {
        return TextType;
    }




    public void setTextType(String textType) {
        TextType = textType;
    }




    public String getIdConversation() {
        return IdConversation;
    }




    public void setIdConversation(String idConversation) {
        IdConversation = idConversation;
    }




    public String getIdSunshineUser() {
        return idSunshineUser;
    }




    public void setIdSunshineUser(String idSunshineUser) {
        this.idSunshineUser = idSunshineUser;
    }




    public String getContend() {
        return contend;
    }




    public void setContend(String contend) {
        this.contend = contend;
    }




    public String getSubContend() {
        return subContend;
    }




    public void setSubContend(String subContend) {
        this.subContend = subContend;
    }




    public Date getAddDate() {
        return addDate;
    }




    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    


    




    
}
