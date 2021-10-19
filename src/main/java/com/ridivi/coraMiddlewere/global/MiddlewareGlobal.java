package com.ridivi.coraMiddlewere.global;


import org.json.JSONObject;

public class MiddlewareGlobal {

    public static String TYPE_LOG_INFO = "INFO";
    public static String TYPE_LOG_ERROR = "ERROR";
    public static String TYPE_LOG_SUCCESS = "SUCCESS";

    public static final int TYPE_MESSAGE_TEXT = 1;
    public static final int TYPE_MESSAGE_IMG = 2;
    public static final int TYPE_MESSAGE_LOCATION = 3;
    public static final int TYPE_MESSAGE_LINK = 4;
    public static final int TYPE_MESSAGE_REPLY = 5;
    
    
    public static String convertObjectToJson(Object obj){
        String jsonString = "";        
        try {
            jsonString = new JSONObject((String) obj).toString();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return jsonString;
    }
    
}
