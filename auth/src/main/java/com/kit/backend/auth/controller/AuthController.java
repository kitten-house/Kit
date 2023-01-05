package com.kit.backend.auth.controller;


import com.kit.backend.auth.Tokens.JWTTokens;
import com.kit.backend.auth.entity.Users;
import com.kit.backend.auth.model.LoginRequestBody;
import com.kit.backend.auth.service.GoogleService;
import org.springframework.web.bind.annotation.*;


@RestController
public class AuthController {

    class AuthResponseBody {
        public String access_token;
        public String refresh_token;

        public AuthResponseBody(String access_token, String refresh_token) {
            this.access_token = access_token;
            this.refresh_token = refresh_token;
        }
    }

    private JWTTokens token;
    private GoogleService googleService;

    @PostMapping("/login") // https://www.googleapis.com/oauth2/v1/userinfo?alt=json
    public AuthResponseBody login(@RequestBody LoginRequestBody body) {

        Users user = googleService.googleRequest(body);
        return new AuthResponseBody(token.accessToken(user), token.refreshToken(user));




        // 1. get users data from https://www.googleapis.com/oauth2/v1/userinfo?alt=json
        // with header - Authorization: Bearer ${TOKEN}

        // 2. check google_id in DB
        // if exists - generate access_token and refresh_token
        // else - create user -> generate access_token and refresh_token
        //
        // 3. return access_token and refresh_token to user
    }
}

