package com.generisdevelopers.stringmetrics;

public class RequestError {
    String field;
    String message;

    public RequestError(){}

    public RequestError(String field, String message){
        this.message = message;
        this.field = field;
    }
    
    public String getField() {
        return field;
    }
    public void setField(String field) {
        this.field = field;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    
}
