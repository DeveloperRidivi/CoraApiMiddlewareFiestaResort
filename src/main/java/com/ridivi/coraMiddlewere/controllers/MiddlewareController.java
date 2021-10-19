package com.ridivi.coraMiddlewere.controllers;

import org.json.JSONObject;
import javax.servlet.http.HttpServletRequest;
import com.ridivi.coraMiddlewere.model.service.IMiddlewareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.ridivi.coraMiddlewere.model.entity.Responce;
import com.ridivi.coraMiddlewere.model.entity.Message;

@RestController
public class MiddlewareController {

    @Autowired
    private IMiddlewareService service;

    //metodos expuestos para consumir

    @RequestMapping("/")
	@ResponseBody
	public String home( HttpServletRequest request ) {
		StringBuilder sb=new StringBuilder();
		sb.append( "<h1>Hello World</h1>" );
		return sb.toString();
	}

    @PostMapping("/ReceivedMessage")
    public Responce ReceivedMessage(@RequestBody String rest){
        return service.ReceivedMessage(rest);
    }

    @PostMapping("/SendMessage")
    public Responce SendMessage(@RequestBody String rest){

        JSONObject json = new JSONObject(rest);
        json = new JSONObject(rest);

        Message message = new Message(json.getString("contend"), null, json.getInt("type"), json.getString("idConversation"), null);
        //Message message = new Message(contend, subContend, type, idConversation, replyList);
        return service.SendMessage(message);
    }

}
