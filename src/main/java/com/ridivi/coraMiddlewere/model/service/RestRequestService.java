package com.ridivi.coraMiddlewere.model.service;
import com.ridivi.coraMiddlewere.global.ErrorCode;
import com.ridivi.coraMiddlewere.model.entity.ErrorDetails;
import com.ridivi.coraMiddlewere.model.entity.Responce;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

@Service
public class RestRequestService {

    @Value("${GroupID}")
    private String GroupID; 

    @Value("${consult.sendTo.Point}")
    private String Point; 

    public Responce SendPost(JSONObject pJson) {
        Responce res = new Responce();
        res.setMessage("");
        try {

            // String url = servicio.getUri() + "/" + pPoint;
            String url = "http://localhost:62431" + "/" + Point;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<String>(pJson.toString(), headers);
            RestTemplate restTemplate = new RestTemplate();
            JSONObject json = restTemplate.postForObject(url, entity, JSONObject.class);
            res.setError(false);
            res.setErrorDetail(null);
            res.setMessage(json.toString());
            return res;
        } catch (Exception e) {

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