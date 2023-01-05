package com.kit.backend.auth.Tokens;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.kit.backend.auth.entity.Users;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.time.Instant;

public class JWTTokens {

    private static String PRIV = "-----BEGIN OPENSSH PRIVATE KEY-----\n" +
            "b3BlbnNzaC1rZXktdjEAAAAACmFlczI1Ni1jdHIAAAAGYmNyeXB0AAAAGAAAABCJ7VOl5m\n" +
            "23TvzriO0ncMjgAAAAEAAAAAEAAAIXAAAAB3NzaC1yc2EAAAADAQABAAACAQDHNj5fReiX\n" +
            "qlGBZmVh0b/0KtgwxzPx4Q4kBIDh0HwZ0VuIIPF4LJIUPLuqGz1joxXI59sdL57Zs9FAoo\n" +
            "cYrgd1sxF2ueRnruQiz+BGmsKDeHNkwaOzc6+s9JwBLOJ1g66QxkeqOzErZQ79Wxk0I0d4\n" +
            "OPJ6U4Wy/88aZQvoOX854Xx173A6W5LJOXxi49WMVGR91V/Zta9YLdz3TXnr4b9QO8fLzX\n" +
            "HaBgg3lf6E+E2gnZmz023kN0wMpbHzMfqRGkaPSBXaJxTapdl2HGobhsvrgnB6sIYHo4hS\n" +
            "uB2U7yJcCfB2Rt9kbdosUKnIBzrMSQ1hKWtoIifp4BTDDRRoREdKBbZ9L1S0U/GWhCihTH\n" +
            "Ml9HRzB57DOiD1EOLdfM/TgWG3xiht+9dVclehZcAm/qOZL0zRqIUM0HXmO2OwXBovvKKA\n" +
            "sFWZEL9mExl88tPtuBgS7jXvmVgszjPBDGs5cer4B76GA3Gf16oacP6GhymWOURVksMkOF\n" +
            "YeREb0DCYctLBcEyEqNb7sWSI70SC4viDHJfyy3Ui9zMuhL47T7PYZakLq/I1BKwsDJi+8\n" +
            "TumOgBKWP7OOTQ93icRa1EyTP5RGC2ntACZ38mJTKtnGv1709fPrA1cPiHtKjopNJJ7MBd\n" +
            "i1NeQMpFLYITfekVZbTRF+Umd0RRLkbcbhFzMNkLeyJQAAB2CTMRpUsxqF/pdZ/VF2CALI\n" +
            "qHaxlN+XYANzKCn5fCoWcMpSf9tpPyqoyYIrZRyShO8HW3nrmo6ajYRnf16DrhC2uQ38UL\n" +
            "vLu6Bv7jnbbZH9K2w6nnrqBcAsajPxAYSnYzdK561VDQWAc7+bxcW6SF6Xq0AyFWwVWV5j\n" +
            "O1SWsPp7PGSnKsfIrqFM6HmGxgKSIhBJBkPAFE1S6cNtWxeqQlgajw2UYSmere2Z0Sl0ed\n" +
            "t8XPBpvs0UjGIBDaLEkgGbooTYqRePIr4BwvrzNiChb9t33o+MIHzRCAhClUelwGw86kzb\n" +
            "XK3NiVdWxic+dBwIclcnF7MM48tq/6M8sl4n/lXbz6BDK5hc/JLPno3TwPILe6DATF4XEJ\n" +
            "QV799FrufpnUgbQajimtBGMeEN1MVpdh1UNrYkW/ho1z6NMunG0NfJsspGakXXQ0pTbX1/\n" +
            "L5SdNi/Qz8f9J+v0NqUZZddKd2Y9PLlDjjQVZWw6a23KUAL2L/MjDy3tdNESF1N0raShAD\n" +
            "hhmxNt+rLMVGL3cIWouzueZanwKlI2EW67VbKpN94im4SW0aU5C6uiZBobQzpOmiI3h318\n" +
            "fAy+Zuszqqs1hFD/R2CUYSFRNvUBcZIcbZ3VaUs41vA8ulY8BwuRESt0VCzg+KjHAsbRfW\n" +
            "3mW+++IiAer+1KVAkVubkuOq5MRZyS82G2Ew7gTEUWKUEeS+/m4lFctj5o2mo+yWHfd0Yn\n" +
            "a9Q+syxZymZ4X9gbRjkEqbEJlW7pzHNvHi67vSzL/udBXPqG6UFUMI1Vnnf+5Pee8YQ+Jd\n" +
            "jqzlm2ou3pD8MLQ+NYZzRBHBRdsvawLpEkgpXtNjja1/RWGrW8LSKZnWjJGn7BBQsUKIpp\n" +
            "2vd2Atfx4NZ96hAH0NQ7W6mwcz8cVrFdlannOkWT7sNJhV6U+dOzH8c1HGg8AfHuUFWrWr\n" +
            "lS9HLqK6M8ctZ/gjNF+pV0lK8cIsRGFYfI4UVN6j0SEtCzPgENzHBTHYZCIn5rFY3CXYPb\n" +
            "jDAmfT/tgDKDqPmnZOhqvQ8C0dO4Y0VkQy5hDAiWsZXoBmuJ5gCI3Nw6EE+I3t5LAuCsao\n" +
            "FXV3q01Xad1AJexbYHuBPMetEHYMO7RzfTJL6pM3u+ktmaVhY8+iwJMqo/sH33yjH39gVR\n" +
            "6y6S3uerq93jGUjGmWcZNGC2pIBdYu+4x9TdtDUVLqADZyp/EqhNOl8KeBiRkJAmdBzQxr\n" +
            "Pub8mD7BOU7CBNUlNZzqYr7qKE9b2Rk4syjeWDu/RXeb33A6cjwH0yJVNT7YWPh9gTs7jd\n" +
            "7+3TZ5FPrpy3pehQJN63gzc2qk/QypWxK15CrNebFcGn/cD/4riP0jYoCZMp2YBJPpwb36\n" +
            "PHKLwDuQA1QkW4QEjswjG2Xt0SnoCR3AUk01AXB7QrsPkO/au41fSS7hsP8lVzNX3vfgWB\n" +
            "cayMV3bGX9wMq8tGdtFRbih5Up1AwCS2np5VHzMK2N3J23BaL/HVkthEC8ZMepCgqOtuZU\n" +
            "pGlckjOa1iS88NMd7k9khLCr6QXsS9NzM1e1TNJCpQElxGY93d9xC84yeg/ej/CKZQCgdb\n" +
            "ojdCmfkOnotCJBhSh7PfU6yu30WxzaC0qealSLH85MxnkRjGaX1iunb8W65wQ8iR83e6uH\n" +
            "ACw/ROZXO9KZ29l5QmIZo8bi7CzTfaY3t2iESO3lQNgTCAOxmC7It/j9VBouLj+FFErzF1\n" +
            "xGROxHyVV6ftgBfSaPPLu4FJCEGQhnSmeFtzzhS13NNyXhMzPj1mVn/tiMIKIkQUC5y/VH\n" +
            "THp515kSPjyNbraXHdJ6veYtZ8pzvB0BGPoS+1TuBa/0ZDqMtDe4Pnots5ObX0UZouDXK0\n" +
            "AUQDkIy+cW2S+q4gtst4VymtebMqbldUvrFVMrnb/FyGIN07IZtjhzZ6BPhM7L5QECM1JA\n" +
            "6lj4Yh19ikPNZX1/QI9CzD/3nrwRg8J+QKvO9mjAWVretn9V4LLj3qNxvP/3un8caaj5Ta\n" +
            "+845FXQjaSE1ERluEJMEGqZCbM/+oid8pNB900qI7GON23BZSpPq3cxYeKQr+gyiZ/bpCa\n" +
            "FqldlIjzghAofW0poDwSdKhTK5a47ZEknAUWNXGP8mfwqSwqfk6Ufg69vQvP3X7lkJKtf+\n" +
            "2UsOaCL4EXBsGJSABH97jsBNDJFpoFDkzcLk42WtZ1kAuEzg3gxYPxRzu6lC08/7L9g/fe\n" +
            "+o8lPTqQySBgupELnFWv/XuZAPagG3f3/xEuPZZ6X3O3Xd5TqN2n9CnT5eQzWejRLkXGWk\n" +
            "MpgxWmfM7mJ893+Bnf1eIJbrPFZEDH+iAsBWWTl7o2w1Nrqi0vCkiCoS3VVON5B4eashA1\n" +
            "cxfYDy50GR5O6ukQ0lgz3pTp5fFc/4F+veDwh/0eL8eZDp4GiUPYOk9GMCqG3vdTbOMw5c\n" +
            "Vkv3lQMi1lhP8m0PKpEdGGvKhQ86LGcociJPAsC7JRBkKrQGU8QKMt+HQBYR3ZDNd3xg9B\n" +
            "IoFXO6MwdPFq2q4lMw4q+2AiHMil7IE17NEtHtHe4/xciz\n" +
            "-----END OPENSSH PRIVATE KEY-----";
    private static String PUB = "ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAACAQDHNj5fReiXqlGBZmVh0b/0KtgwxzPx4Q4kBIDh0HwZ0VuIIPF4LJIUPLuqGz1joxXI59sdL57Zs9FAoocYrgd1sxF2ueRnruQiz+BGmsKDeHNkwaOzc6+s9JwBLOJ1g66QxkeqOzErZQ79Wxk0I0d4OPJ6U4Wy/88aZQvoOX854Xx173A6W5LJOXxi49WMVGR91V/Zta9YLdz3TXnr4b9QO8fLzXHaBgg3lf6E+E2gnZmz023kN0wMpbHzMfqRGkaPSBXaJxTapdl2HGobhsvrgnB6sIYHo4hSuB2U7yJcCfB2Rt9kbdosUKnIBzrMSQ1hKWtoIifp4BTDDRRoREdKBbZ9L1S0U/GWhCihTHMl9HRzB57DOiD1EOLdfM/TgWG3xiht+9dVclehZcAm/qOZL0zRqIUM0HXmO2OwXBovvKKAsFWZEL9mExl88tPtuBgS7jXvmVgszjPBDGs5cer4B76GA3Gf16oacP6GhymWOURVksMkOFYeREb0DCYctLBcEyEqNb7sWSI70SC4viDHJfyy3Ui9zMuhL47T7PYZakLq/I1BKwsDJi+8TumOgBKWP7OOTQ93icRa1EyTP5RGC2ntACZ38mJTKtnGv1709fPrA1cPiHtKjopNJJ7MBdi1NeQMpFLYITfekVZbTRF+Umd0RRLkbcbhFzMNkLeyJQ== andrew@MacBook-Pro-Ksenny.local";
    private static String ISSUER = "kit-auth-server";

    private static long ACCESS_LIFETIME = 60 * 30;
    private static long REFRESH_LIFETIME = 60 * 60 * 24 * 60;

    private static long VERSION = 1;


    @Nullable
    public String accessToken(Users user) {
        try {
            Instant now = Instant.now();
            Instant expired = now.plusSeconds(ACCESS_LIFETIME);


            Algorithm algorithm = Algorithm.HMAC512(PUB);
            String Acctoken = JWT.create()
                    .withIssuer(ISSUER)
                    .withClaim("version", VERSION)
                    .withClaim("id", user.getId())
                    .withClaim("name", user.getName())
                    .withClaim("avatar", user.getName())
                    .withIssuedAt(now)
                    .withNotBefore(now)
                    .withExpiresAt(expired)
                    .sign(algorithm);
            return Acctoken;
        } catch (
                JWTCreationException exception) {
            return null;
        }
    }


    public String refreshToken(Users user) {
        try {
            Instant now = Instant.now();
            Instant expired = now.plusSeconds(REFRESH_LIFETIME);

            Algorithm algorithm = Algorithm.HMAC512(PUB);
            String RefreshToken = JWT.create()
                    .withIssuer(ISSUER)
                    .withClaim("id", user.getId())
                    .withIssuedAt(now)
                    .withNotBefore(now)
                    .withExpiresAt(expired)
                    .sign(algorithm);
            return RefreshToken;

        } catch (
                JWTCreationException exception) {
            return "sovsem kapec";
        }
    }
}

