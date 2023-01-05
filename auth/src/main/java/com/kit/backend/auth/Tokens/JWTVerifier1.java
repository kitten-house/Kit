package com.kit.backend.auth.Tokens;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.kit.backend.auth.UsersDAO.UsersRepository;
import com.kit.backend.auth.controller.AuthController;
import com.kit.backend.auth.entity.Users;
import com.kit.backend.auth.model.AuthResponseBody;
import jakarta.annotation.Nullable;

public class JWTVerifier1 {
    private UsersRepository usersRepository;
    private JWTTokens JWTtoken;
    private static String PUB = "ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAACAQDHNj5fReiXqlGBZmVh0b/0KtgwxzPx4Q4kBIDh0HwZ0VuIIPF4LJIUPLuqGz1joxXI59sdL57Zs9FAoocYrgd1sxF2ueRnruQiz+BGmsKDeHNkwaOzc6+s9JwBLOJ1g66QxkeqOzErZQ79Wxk0I0d4OPJ6U4Wy/88aZQvoOX854Xx173A6W5LJOXxi49WMVGR91V/Zta9YLdz3TXnr4b9QO8fLzXHaBgg3lf6E+E2gnZmz023kN0wMpbHzMfqRGkaPSBXaJxTapdl2HGobhsvrgnB6sIYHo4hSuB2U7yJcCfB2Rt9kbdosUKnIBzrMSQ1hKWtoIifp4BTDDRRoREdKBbZ9L1S0U/GWhCihTHMl9HRzB57DOiD1EOLdfM/TgWG3xiht+9dVclehZcAm/qOZL0zRqIUM0HXmO2OwXBovvKKAsFWZEL9mExl88tPtuBgS7jXvmVgszjPBDGs5cer4B76GA3Gf16oacP6GhymWOURVksMkOFYeREb0DCYctLBcEyEqNb7sWSI70SC4viDHJfyy3Ui9zMuhL47T7PYZakLq/I1BKwsDJi+8TumOgBKWP7OOTQ93icRa1EyTP5RGC2ntACZ38mJTKtnGv1709fPrA1cPiHtKjopNJJ7MBdi1NeQMpFLYITfekVZbTRF+Umd0RRLkbcbhFzMNkLeyJQ== andrew@MacBook-Pro-Ksenny.local";
    private static String ISSUER = "kit-auth-server";

    @Nullable
    public AuthResponseBody verifyJWT (String token) {
        DecodedJWT decodedJWT;

        try {
            Algorithm algorithm = Algorithm.HMAC512(PUB);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .withClaimPresence("id")
//                    .withClaimPresence("name")
//                    .withClaimPresence("avatar")
                    .build();

            decodedJWT = verifier.verify(token);
            Claim a = decodedJWT.getClaim("id");
            long userId = a.asLong();
            Users user = usersRepository.getReferenceById(userId);

            return new AuthResponseBody(JWTtoken.accessToken(user), JWTtoken.refreshToken(user));

        } catch (JWTVerificationException exception) {
            // Invalid signature/claims
            return null;
        }
    }
}
