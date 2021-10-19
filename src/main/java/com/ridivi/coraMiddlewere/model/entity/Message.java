package com.ridivi.coraMiddlewere.model.entity;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Message {


    @JsonProperty("contend")
    private String contend;   //depending on type: text, url, url, lat, url

    @JsonProperty("subContend")
    private String subContend;//depending on type: "",text,text, long, text
    
    @JsonProperty("type")
    private int type;

    @JsonProperty("idConversation")
    private String idConversation;

    @JsonProperty("reply")
    private ArrayList<String> replyList;



    public Message(String contend, String subContend, int type, String idConversation, ArrayList<String> replyList) {
        this.contend = contend;
        this.subContend = subContend;
        this.type = type;
        this.idConversation = idConversation;
        this.replyList = replyList;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getIdConversation() {
        return idConversation;
    }

    public void setIdConversation(String idConversation) {
        this.idConversation = idConversation;
    }

    public ArrayList<String> getReplyList() {
        return replyList;
    }

    public void setReplyList(ArrayList<String> replyList) {
        this.replyList = replyList;
    }



    

    
}
