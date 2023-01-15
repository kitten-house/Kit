package com.kit.backend.auth.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GoogleAccount {
    @JsonProperty("sub")
    public String id;
    public String name;
    public String picture;
    public String email;

    @Override
    public String toString() {
        return "GoogleAccount{" +
                "sub='" + id + '\'' +
                ", name='" + name + '\'' +
                ", picture='" + picture + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
