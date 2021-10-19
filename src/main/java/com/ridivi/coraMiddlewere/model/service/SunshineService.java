package com.ridivi.coraMiddlewere.model.service;



import org.springframework.beans.factory.annotation.Value;

import com.zendesk.sunshine_conversations_client.ApiClient;
import com.zendesk.sunshine_conversations_client.Configuration;
import com.zendesk.sunshine_conversations_client.auth.*;
import com.zendesk.sunshine_conversations_client.model.*;
import com.zendesk.sunshine_conversations_client.api.MessagesApi;


import com.ridivi.coraMiddlewere.global.ErrorCode;
import com.ridivi.coraMiddlewere.model.entity.ErrorDetails;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
public class SunshineService {


    

    @Value("${Sunshine.appId}")
    private String appId; 

    @Value("${Sunshine.Path}")
    private String Path; 

    @Value("${Sunshine.Username}")
    private String Username; 

    @Value("${Sunshine.Password}")
    private String Password; 
    
    private ApiClient defaultClient = null;
    private HttpBasicAuth basicAuth = null;



    /*hmm
     * Entradas: texto a enviar, conversacion a la cual se desea enviar el mensaje
     * Salida: Mensaje enviado a la conversacion indicada
     * Restricciones: N/A
     * Observaciones: esta funcion se puede usar para mandar un link.
     * */
    public ErrorDetails sendText(String pText, String pConversationId) {


        try {

            //configuracion de conexion con sunshine
            this.defaultClient = Configuration.getDefaultApiClient();
            this.defaultClient.setBasePath(Path);
            this.basicAuth = (HttpBasicAuth) defaultClient.getAuthentication("basicAuth");
            basicAuth.setUsername(Username); 
            basicAuth.setPassword(Password);
            MessagesApi apiInstance = new MessagesApi(this.defaultClient);

            MessagePost messagePost = new MessagePost();

          //author
          Author emissary = new Author();
          emissary.setType(Author.TypeEnum.BUSINESS);
          messagePost.author(emissary);

            //message
            TextMessage contend = new TextMessage();
            contend.setType("text");
            contend.setText(pText);

            messagePost.setContent(contend);

            apiInstance.postMessage(messagePost, this.appId, pConversationId);

            return null;

        } catch (Exception e ) {
            System.out.println(e.getMessage());
            return new ErrorDetails(ErrorCode.ERROR_0001,ErrorCode.ERROR_0001_DESC,e.getMessage());
        }
    }

    /*hmm
     * Entradas: url de la imagen, conversacion a la cual se desea enviar el mensaje
     * Salida: Mensaje enviado a la conversacion indicada
     * Restricciones: la imagen debe ser accesible para corachat
     * Observaciones: N/A
     * */
    public ErrorDetails sendImg(String pImgUrl,String pText ,String pConversationId) {

        try{

            //configuracion de conexion con sunshine
            this.defaultClient = Configuration.getDefaultApiClient();
            this.defaultClient.setBasePath(Path);
            this.basicAuth = (HttpBasicAuth) defaultClient.getAuthentication("basicAuth");
            basicAuth.setUsername(Username); 
            basicAuth.setPassword(Password);

            MessagePost messagePost = new MessagePost();

            //author
            Author emissary = new Author();
            emissary.setType(Author.TypeEnum.BUSINESS);
            messagePost.author(emissary);


            //imagen
            ImageMessage contend = new ImageMessage();
            contend.setType("image");
            contend.setMediaUrl(URI.create(pImgUrl));
            if(pText != ""){
                contend.setText(pText);
            }

            messagePost.setContent(contend);
            MessagesApi apiInstance = new MessagesApi(this.defaultClient);
            apiInstance.postMessage(messagePost, this.appId, pConversationId);
            
            return null;
        } catch (Exception e ) {
            System.out.println(e.getMessage());
            return new ErrorDetails(ErrorCode.ERROR_0001,ErrorCode.ERROR_0001_DESC,e.getMessage());
        }
    }

    /*hmm
     * Entradas: PLat => latitud de la ubicacion, PLong => Longitud de la ubicacion y
     * pConversationId => conversacion a la cual se desea enviar el mensaje
     * Salida: Mensaje enviado a la conversacion indicada
     * Restricciones: N/A
     * Observaciones: se debe usar las referencias geograficas que usa google maps
     * */
    public ErrorDetails sendLoc(String pLat, String pLong, String pConversationId) {

        try {

            //configuracion de conexion con sunshine
            this.defaultClient = Configuration.getDefaultApiClient();
            this.defaultClient.setBasePath(Path);
            this.basicAuth = (HttpBasicAuth) defaultClient.getAuthentication("basicAuth");
            basicAuth.setUsername(Username); 
            basicAuth.setPassword(Password);

            MessagePost messagePost = new MessagePost();

            //author
            Author emissary = new Author();
            emissary.setType(Author.TypeEnum.BUSINESS);
            messagePost.author(emissary);

            //location
            LocationMessage contend = new LocationMessage();
            contend.setType("location");
            LocationMessageCoordinates location = new LocationMessageCoordinates();
            location.setLat(new BigDecimal(pLat));
            location.setLong(new BigDecimal(pLong));
            contend.setCoordinates(location);

            messagePost.setContent(contend);
            MessagesApi apiInstance = new MessagesApi(this.defaultClient);
            apiInstance.postMessage(messagePost, this.appId, pConversationId);
            return null;
        } catch (Exception e ) {
            System.out.println(e.getMessage());
            return new ErrorDetails(ErrorCode.ERROR_0001,ErrorCode.ERROR_0001_DESC,e.getMessage());
        }
    }
   
    /*hmm
     * Entradas: pLink => link que se desea enviar, pText => como se ve el link y
     * pConversationId => conversacion a la cual se desea enviar el mensaje
     * Salida: Mensaje enviado a la conversacion indicada con el link
     * Restricciones: N/A
     * Observaciones: en ciertos canales el texto solo se pone a la par del link
     * */
    public ErrorDetails sendLink(String pLink , String pText, String pConversationId) {

        try {

            //configuracion de conexion con sunshine
            this.defaultClient = Configuration.getDefaultApiClient();
            this.defaultClient.setBasePath(Path);
            this.basicAuth = (HttpBasicAuth) defaultClient.getAuthentication("basicAuth");
            basicAuth.setUsername(Username); 
            basicAuth.setPassword(Password);

            MessagePost messagePost = new MessagePost();

            //author
            Author emissary = new Author();
            emissary.setType(Author.TypeEnum.BUSINESS);
            messagePost.author(emissary);

            //message
            TextMessage contend = new TextMessage();
            contend.setType("text");
            contend.setText(pText);

            //reply
            List<Action> list = new ArrayList<>();

            Link link = new Link();
            link.setType("link");
            link.setUri(URI.create(pLink));
            list.add(link);
            
            contend.setActions(list);
            messagePost.setContent(contend);
            MessagesApi apiInstance = new MessagesApi(this.defaultClient);
            apiInstance.postMessage(messagePost, this.appId, pConversationId);
            return null;
        } catch (Exception e ) {
            System.out.println(e.getMessage());
            return new ErrorDetails(ErrorCode.ERROR_0001,ErrorCode.ERROR_0001_DESC,e.getMessage());
        }
    }
    
    /*hmm
     * Entradas: lista de replay, texto del mensaje,conversacion a la cual se desea enviar el mensaje
     * Salida: Mensaje enviado a la conversacion indicada con los replays
     * Restricciones: N/A
     * Observaciones: esta funcion se usa solo para chats que tengan replay como telegram
     * */
    public ErrorDetails sendListados(List<String> pReply,String pText, String pConversationId) {

        try {

            //configuracion de conexion con sunshine
            this.defaultClient = Configuration.getDefaultApiClient();
            this.defaultClient.setBasePath(Path);
            this.basicAuth = (HttpBasicAuth) defaultClient.getAuthentication("basicAuth");
            basicAuth.setUsername(Username); 
            basicAuth.setPassword(Password);
            MessagePost messagePost = new MessagePost();

            //author
            Author emissary = new Author();
            emissary.setType(Author.TypeEnum.BUSINESS);
            messagePost.author(emissary);

            //message
            TextMessage contend = new TextMessage();
            contend.setType("text");
            contend.setText(pText.replace("*", ""));

            List<Action> list = new ArrayList<>();

            for(int i =0;pReply.size() > i; i++){
                Postback action = new Postback();
                action.setText(pReply.get(i).replace("*", ""));
                action.setType("postback");
                action.setPayload(Integer.toString(i+1).toString());
                list.add(action);
            }
            if(list.size()>0){
                contend.setActions(list);
            }

            messagePost.setContent(contend);
            MessagesApi apiInstance = new MessagesApi(this.defaultClient);
            apiInstance.postMessage(messagePost, this.appId, pConversationId);
            return null;
        } catch (Exception e ) {
            System.out.println(e.getMessage());
            return new ErrorDetails(ErrorCode.ERROR_0001,ErrorCode.ERROR_0001_DESC,e.getMessage());
        }
    }    
   
}




