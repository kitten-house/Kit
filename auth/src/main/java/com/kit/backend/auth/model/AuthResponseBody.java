package com.kit.backend.auth.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthResponseBody {

    @JsonProperty("access_token")
    public String accessToken;

    @JsonProperty("refresh_token")
    public String refreshToken;

    public AuthResponseBody(String access_token, String refresh_token) {
        this.accessToken = access_token;
        this.refreshToken = refresh_token;
    }
}
