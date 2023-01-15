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

    @PostMapping("/login") // https://www.googleapis.com/oauth2/v1/userinfo?alt=json
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

    String token = "eyJhbGciOiJSUzI1NiIsImtpZCI6ImQzN2FhNTA0MzgxMjkzN2ZlNDM5NjBjYTNjZjBlMjI4NGI2ZmMzNGQiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20iLCJuYmYiOjE2NzM3ODY3ODgsImF1ZCI6Ijg5NTA2MTIxNzM0MC12Y2tqMmdiaGppZWtwaGtnNWxnM2Y2ZWFmMWU1YzB0di5hcHBzLmdvb2dsZXVzZXJjb250ZW50LmNvbSIsInN1YiI6IjExNTYzNjA5NTE5MDA2ODY3MTI1MCIsImVtYWlsIjoiYW5kcmV3Y2h1cGluOTZAZ21haWwuY29tIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsImF6cCI6Ijg5NTA2MTIxNzM0MC12Y2tqMmdiaGppZWtwaGtnNWxnM2Y2ZWFmMWU1YzB0di5hcHBzLmdvb2dsZXVzZXJjb250ZW50LmNvbSIsIm5hbWUiOiJBbmRyZXcgQ2h1cGluIiwicGljdHVyZSI6Imh0dHBzOi8vbGgzLmdvb2dsZXVzZXJjb250ZW50LmNvbS9hL0FFZEZUcDRqbTJVWU1rWXFSV01EUkpkOXB3QTltR21xNG5PTnV6WlhweDB3PXM5Ni1jIiwiZ2l2ZW5fbmFtZSI6IkFuZHJldyIsImZhbWlseV9uYW1lIjoiQ2h1cGluIiwiaWF0IjoxNjczNzg3MDg4LCJleHAiOjE2NzM3OTA2ODgsImp0aSI6ImU0ZTZlZjFiZmUxMjYzOTA5OWVlMTRhNDcwNDM1MzdmNzdjZmM2YTQifQ.ZuBqKt7ontpAcVTZga036l3MI5s6WHg7Fbk03XSKRm2TRuCoei0x42fQsHJ7V1dKDHq2O3zzHljEE7qo6ujVpnPASrqftO90hcY9-48vazdc9YPD2FTDv0VSN0fP8nFykt8N0l-HehguBUr_sdIjF8VBDGfnTH2V5q3ffjEMPbnmfq960oHdGDbXXyzK88jYAnIGGB5Trl2Kl8qn3Tu1PlyigeKpa5kk01TYYQcXM4sHAcK2Cp0GHHaNKJZ3EeL2nyleFXCahrgW-B5eGp1j4PVNveLx-ZHa1QT3EGOrKBWJgzol10FWjbMlIHYMvRo3qJGihZktuumY5pXkwDw8jg";

    @PostMapping("/me")
    public ResponseEntity<User> getUser(
//        @Nullable
//        @CookieValue(name = "refreshToken", required = false)
//        String refreshToken,
//
//        @Nullable
//        @RequestHeader(name = "Authorization", required = false)
//        String authorization
        )
    {
        System.out.println("BLA1");
//        Optional<String> token = validateToken(refreshToken, authorization);
//
//        if (token.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//
//        Optional<User> optional = authServiceImp.getUserByToken(token.get());
//
//        if (optional.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }

        RestTemplate template = new RestTemplate();
        String url = "https://www.googleapis.com/oauth2/v3/tokeninfo?id_token=" + token;

        try {
            Logger.getGlobal().log(Level.INFO,"BLA1");
            HttpEntity<String> entity = new HttpEntity<>(new HttpHeaders());
            ResponseEntity<GoogleAccount> result = template.exchange(url, HttpMethod.GET, entity, GoogleAccount.class);
            Logger.getGlobal().log(Level.INFO,"BLA2");
            GoogleAccount json = result.getBody();
            Logger.getGlobal().log(Level.INFO, "BLABLA " + json + " dsa " + result.getStatusCode());
        } catch (Throwable e) {
            e.printStackTrace();
        }

        User user = new User();
        user.setId(1);
        user.setName("Andrew Chupin");
        user.setAvatar("ava");
        user.setGoogleId("2321");

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        return ResponseEntity.ok().headers(responseHeaders).body(user);
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

