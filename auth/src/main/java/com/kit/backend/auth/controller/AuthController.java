package com.kit.backend.auth.controller;


import com.kit.backend.auth.Tokens.JWTTokens;
import com.kit.backend.auth.Tokens.JWTVerifier1;
import com.kit.backend.auth.entity.Users;
import com.kit.backend.auth.model.AuthResponseBody;
import com.kit.backend.auth.model.LoginRequestBody;
import com.kit.backend.auth.service.GoogleService;
import org.springframework.web.bind.annotation.*;

// 1. get users data from https://www.googleapis.com/oauth2/v1/userinfo?alt=json
// with header - Authorization: Bearer ${TOKEN}

// 2. check google_id in DB
// if exists - generate access_token and refresh_token
// else - create user -> generate access_token and refresh_token
//
// 3. return access_token and refresh_token to user


@RestController
public class AuthController {

    private JWTTokens token;
    private GoogleService googleService;
    private JWTVerifier1 jwtVerifier1;

    @PostMapping("/login") // https://www.googleapis.com/oauth2/v1/userinfo?alt=json
    public AuthResponseBody login(@RequestBody LoginRequestBody body) {

        Users user = googleService.googleRequest(body);
        return new AuthResponseBody(token.accessToken(user), token.refreshToken(user));
    }

    @PostMapping("/refresh")
    public AuthResponseBody refresh (@RequestBody String token){
        return jwtVerifier1.verifyJWT(token);
    }
}

