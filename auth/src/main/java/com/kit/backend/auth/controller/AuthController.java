package com.kit.backend.auth.controller;

import com.kit.backend.auth.entity.TokenType;
import com.kit.backend.auth.entity.User;
import com.kit.backend.auth.model.AuthResponseBody;
import com.kit.backend.auth.model.GoogleAccount;
import com.kit.backend.auth.model.LoginRequestBody;
import com.kit.backend.auth.service.AuthServiceImp;
import com.kit.backend.auth.service.ServiceUser;
import jakarta.annotation.Nullable;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

// 1. get users data from https://www.googleapis.com/oauth2/v1/userinfo?alt=json
// with header - Authorization: Bearer ${TOKEN}

// 2. check google_id in DB
// if exists - generate access_token and refresh_token
// else - create user -> generate access_token and refresh_token
//
// 3. return access_token and refresh_token to user

@SuppressWarnings("DuplicatedCode")
@RestController
public class AuthController {

    private final ServiceUser serviceUser;
    private final AuthServiceImp authServiceImp;

    public AuthController(ServiceUser serviceUser, AuthServiceImp authServiceImp) {
        this.serviceUser = serviceUser;
        this.authServiceImp = authServiceImp;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseBody> login(
        @RequestBody LoginRequestBody body
    ) {
        HttpHeaders responseHeaders = new HttpHeaders();
        User user = serviceUser.getOrSave(body);

        AuthResponseBody authBody = authServiceImp.generateTokens(user);

        addTokenCookie(TokenType.ACCESS, responseHeaders, authBody.accessToken);
        addTokenCookie(TokenType.REFRESH, responseHeaders, authBody.refreshToken);

        return ResponseEntity.ok().headers(responseHeaders).body(authBody);
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponseBody> refresh(
        @Nullable
        @CookieValue(name = "refreshToken", required = false)
        String refreshToken,

        @Nullable
        @RequestHeader(name = "Authorization", required = false)
        String authorization
    ) {
        Optional<String> token = validateToken(refreshToken, authorization);

        if (token.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Optional<User> optional = authServiceImp.getUserByToken(token.get());

        if (optional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        AuthResponseBody body = authServiceImp.generateTokens(optional.get());

        HttpHeaders responseHeaders = new HttpHeaders();
        addTokenCookie(TokenType.ACCESS, responseHeaders, body.accessToken);
        addTokenCookie(TokenType.REFRESH, responseHeaders, body.refreshToken);

        return ResponseEntity.ok().headers(responseHeaders).body(body);
    }

    public void addTokenCookie(TokenType type, HttpHeaders httpHeaders, String AcToken) {
        httpHeaders.add(HttpHeaders.SET_COOKIE, createTokenCookie(type, AcToken).toString());
    }

    private HttpCookie createTokenCookie(TokenType type, String token) {
        return ResponseCookie.from(type.name, token)
            .maxAge(type.lifetime)
            .httpOnly(true)
            .build();
    }

    @PostMapping("/me")
    public ResponseEntity<User> getUser(
            @Nullable
            @CookieValue(name = "refreshToken", required = false)
            String refreshToken,

            @Nullable
            @RequestHeader(name = "Authorization", required = false)
            String authorization
        )
    {
        Optional<String> token = validateToken(refreshToken, authorization);

        if (token.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Optional<User> optional = authServiceImp.getUserByToken(token.get());

        if (optional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        return ResponseEntity.ok().headers(responseHeaders).body(optional.get());
    }

    private Optional<String> validateToken(String refreshToken, String authorization) {
        String token;

        if (!authorization.equals(ValueConstants.DEFAULT_NONE)) {
            token = authorization;
        } else if (!refreshToken.equals(ValueConstants.DEFAULT_NONE)) {
            token = refreshToken;
        } else {
            return Optional.empty();
        }

        return Optional.of(token);
    }
}

