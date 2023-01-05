package com.kit.backend.auth.model;

public class AuthResponseBody {
    public String access_token;
    public String refresh_token;

    public AuthResponseBody(String access_token, String refresh_token) {
        this.access_token = access_token;
        this.refresh_token = refresh_token;
    }
}
