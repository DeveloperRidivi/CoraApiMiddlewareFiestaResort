package com.ridivi.coraMiddlewere.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorDetails {

    @JsonProperty("codeError")
    private int codeError;

    @JsonProperty("description")
    private String description;

    @JsonProperty("message")
    private String message;
    
    public ErrorDetails() {}

    public ErrorDetails(int codigoError, String description, String message) {
        this.codeError = codigoError;
        this.description = description;
        this.message = message;
    }

    public int getCodeError() {
        return codeError;
    }

    public void setCodeError(int codeError) {
        this.codeError = codeError;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
