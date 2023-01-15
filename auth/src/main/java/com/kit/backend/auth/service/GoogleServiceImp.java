package com.kit.backend.auth.service;

import com.kit.backend.auth.model.GoogleAccount;
import com.kit.backend.auth.model.LoginRequestBody;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GoogleServiceImp implements GoogleService {

    private LoginRequestBody body;
    private GoogleAccount json;

    @Override
    public GoogleAccount googleRequest(LoginRequestBody body) {
        RestTemplate template = new RestTemplate();
        String url = "https://www.googleapis.com/oauth2/v3/tokeninfo?id_token=" + body.data.token;

        HttpEntity<String> entity = new HttpEntity<>(new HttpHeaders());
        ResponseEntity<GoogleAccount> result = template.exchange(url, HttpMethod.GET, entity, GoogleAccount.class);
        GoogleAccount json = result.getBody();
        result.getStatusCode();

        return json;

    }
}
