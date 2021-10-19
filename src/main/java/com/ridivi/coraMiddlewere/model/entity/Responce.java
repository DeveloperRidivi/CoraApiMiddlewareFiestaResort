package com.ridivi.coraMiddlewere.model.entity;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Responce {

    @JsonProperty("error")
    private boolean error;

    @JsonProperty("errorDetail")
    private ErrorDetails errorDetail;

    @JsonProperty("message")
    private String message;

    public Responce() {
    }

    public Responce(boolean error, ErrorDetails errorDetail, String message) {
        this.error = error;
        this.errorDetail = errorDetail;
        this.message = message;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public ErrorDetails getErrorDetail() {
        return errorDetail;
    }

    public void setErrorDetail(ErrorDetails errorDetail) {
        this.errorDetail = errorDetail;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    


}
