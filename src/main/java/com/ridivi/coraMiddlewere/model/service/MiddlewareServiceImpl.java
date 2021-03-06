package com.ridivi.coraMiddlewere.model.service;

import com.ridivi.coraMiddlewere.global.ErrorCode;
import com.ridivi.coraMiddlewere.global.MessageCode;
import com.ridivi.coraMiddlewere.global.MiddlewareGlobal;
import com.ridivi.coraMiddlewere.model.entity.ErrorDetails;
import com.ridivi.coraMiddlewere.model.entity.Message;
import com.ridivi.coraMiddlewere.model.entity.MessageRegister;
import com.ridivi.coraMiddlewere.model.entity.LogOperation;
import com.ridivi.coraMiddlewere.model.entity.Responce;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MiddlewareServiceImpl implements IMiddlewareService {

    @Autowired    
    private LogOperationService LogOperation;   
    
    @Autowired    
    private SunshineService sendSunshine;  

    @Autowired    
    private RestRequestService sendRest;


    @Autowired    
    private MessageRegisterService mesgRegservice;

    @Override
    public Responce ReceivedMessage(String rest){
        Responce res = new Responce();
        try{

            LogOperation.save(new LogOperation("ReceivedMessage", MiddlewareGlobal.TYPE_LOG_INFO+"-Middleware", rest));
            JSONObject json =  new JSONObject(rest);
            
            //saquemos nada mas los valores que importan
            JSONObject newJson =  new JSONObject();

            String idConversacion = json.getJSONObject("conversation").getString("_id");
            String idSunshineUser = json.getJSONObject("appUser").getString("_id");
            String type =  json.getJSONArray("messages").getJSONObject(0).getString("type");
            String channel = "";
            String contend = "";

            if (type.equals("postback")) {
                channel = json.getJSONArray("postbacks").getJSONObject(0).getJSONObject("source").getString("type");
              
            }else {
                type = json.getJSONArray("messages").getJSONObject(0).getString("type");
                channel = json.getJSONArray("messages").getJSONObject(0).getJSONObject("source").getString("type");
            } 

            switch (type) {
                case "text":
                    contend = json.getJSONArray("messages").getJSONObject(0).getString("text");
                    break;
                case "location":
                    contend = json.getJSONArray("messages").getJSONObject(0).getString("coordinates");
                    break;
                case "postback":
                    contend = json.getJSONArray("postbacks").getJSONObject(0).getJSONObject("action").getString("payload");;
                    break;
                default:
                    contend = json.getJSONArray("messages").getJSONObject(0).getString("mediaUrl");
            }

            MessageRegister reg =  new MessageRegister();

            reg.setIdConversation(idConversacion);
            reg.setIdSunshineUser(idSunshineUser);
            reg.setContend(contend);
            reg.setTextType(type);
            reg.setSubContend("");
            reg.setType("received");
            mesgRegservice.save(reg);

            newJson.put("idConversacion", idConversacion);
            newJson.put("idSunshineUser", idSunshineUser);
            newJson.put("type", type);
            newJson.put("channel", channel);
            newJson.put("contend", contend);
            
            res = sendRest.SendPost(newJson);
            res.setMessage(MessageCode.MSG_0001);
            LogOperation.save(new LogOperation("ReceivedMessage",
            (!res.isError() ? MiddlewareGlobal.TYPE_LOG_SUCCESS+"-Middleware" : MiddlewareGlobal.TYPE_LOG_ERROR+"-Middleware"), MiddlewareGlobal.convertObjectToJson(res)));
            return res;
        }
        catch(JSONException  e){
            res.setError(true);
            res.setMessage("");
            ErrorDetails error = new ErrorDetails();
            error.setCodeError(ErrorCode.ERROR_0005);
            error.setDescription(ErrorCode.ERROR_0005_DESC);
            error.setMessage(e.getMessage());
            res.setErrorDetail(error);
            return res;

        }
        catch(Exception e){
            res.setMessage("");
            res.setError(true);
            ErrorDetails error = new ErrorDetails();
            error.setCodeError(ErrorCode.ERROR_0002);
            error.setDescription(ErrorCode.ERROR_0002_DESC);
            error.setMessage(e.getMessage());
            res.setErrorDetail(error);
            return res;
        }

    }
    
    @Override
    public Responce SendMessage(Message rest){
        Responce res = new Responce();
        try{

            //LogOperation.save(new LogOperation("SendMessage", MiddlewareGlobal.TYPE_LOG_INFO, MiddlewareGlobal.convertObjectToJson(rest)));
           
            ErrorDetails error = null;
            String type = "";
            switch (rest.getType()) 
            {
                case MiddlewareGlobal.TYPE_MESSAGE_TEXT:
                    type ="text";
                    error = sendSunshine.sendText(rest.getContend(), rest.getIdConversation());
                    break;
                case MiddlewareGlobal.TYPE_MESSAGE_IMG:
                    type ="img";
                    error = sendSunshine.sendImg(rest.getContend(),rest.getSubContend(),rest.getIdConversation());
                    break;
                case MiddlewareGlobal.TYPE_MESSAGE_LOCATION:
                    type ="location";
                    error = sendSunshine.sendLoc(rest.getContend(),rest.getSubContend(), rest.getIdConversation());
                    break;
                case MiddlewareGlobal.TYPE_MESSAGE_LINK:
                    type ="link";
                    error = sendSunshine.sendLink(rest.getContend(),rest.getSubContend(),rest.getIdConversation());
                    break;
                case MiddlewareGlobal.TYPE_MESSAGE_REPLY:
                    type ="reply";
                    error = sendSunshine.sendListados(rest.getReplyList(),rest.getContend(),rest.getIdConversation());
                    break;
            }
            

            MessageRegister reg =  new MessageRegister();

            reg.setIdConversation(rest.getIdConversation());
            reg.setIdSunshineUser("");
            reg.setContend(rest.getContend());
            reg.setTextType(type);
            reg.setSubContend("");
            reg.setType("sent");
            mesgRegservice.save(reg);


            if(error == null){
                res.setError(false);
                res.setMessage(MessageCode.MSG_0002);
            }else{
                res.setMessage("");
                res.setError(true);
            }

            res.setErrorDetail(error);

            return res;
            
        }
        catch(Exception e){
            res.setMessage("");
            res.setError(true);
            ErrorDetails error = new ErrorDetails();
            error.setCodeError(ErrorCode.ERROR_0002);
            error.setDescription(ErrorCode.ERROR_0002_DESC);
            error.setMessage(e.getMessage());
            res.setErrorDetail(error);
            return res;
        }

    }


    

    

  


}

