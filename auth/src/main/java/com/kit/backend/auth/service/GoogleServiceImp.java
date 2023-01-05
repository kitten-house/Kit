package com.kit.backend.auth.service;

import com.kit.backend.auth.entity.Users;
import com.kit.backend.auth.model.ForJSON;
import com.kit.backend.auth.model.LoginRequestBody;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class GoogleServiceImp implements GoogleService {

    private LoginRequestBody body;
    private ForJSON json;
    private ServiceUser serviceUser;


    @Override
    public Users googleRequest(LoginRequestBody body) {

        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + body.data.token);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<ForJSON> result =
                template.exchange("https://www.googleapis.com/oauth2/v1/userinfo?alt=json", HttpMethod.GET, entity, ForJSON.class);
        ForJSON json = result.getBody();
        Users user = serviceUser.addUser(json);


        return user;

    }

}
