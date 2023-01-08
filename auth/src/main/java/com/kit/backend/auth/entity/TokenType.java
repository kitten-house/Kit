package com.kit.backend.auth.entity;

public enum TokenType {

    ACCESS("accessToken", Const.ACCESS_LIFETIME),
    REFRESH("refreshToken", Const.REFRESH_LIFETIME);

    public final String name;
    public final long lifetime;

    TokenType(String name, long lifetime) {
        this.name = name;
        this.lifetime = lifetime;
    }

    private static class Const {
        public static final long ACCESS_LIFETIME = 60 * 30;
        public static final long REFRESH_LIFETIME = 60 * 60 * 24 * 60;
    }
}
