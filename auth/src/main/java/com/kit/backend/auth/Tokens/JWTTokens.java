package com.kit.backend.auth.Tokens;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.kit.backend.auth.entity.TokenType;
import com.kit.backend.auth.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import java.time.Instant;
import java.util.Optional;

@Component
public class JWTTokens {

    @Value("${com.kit.backend.auth.tokenSecret}")
    private String secret;

    @Value("${com.kit.backend.auth.tokenIssuer}")
    private String issuer;

    @Value("${com.kit.backend.auth.tokenVersion}")
    private long version;

    @Nullable
    public String accessToken(User user) {
        try {
            Instant now = Instant.now();
            Instant expired = now.plusSeconds(TokenType.ACCESS.lifetime);


            Algorithm algorithm = Algorithm.HMAC512(secret);
            String Acctoken = JWT.create()
                .withIssuer(issuer)
                .withClaim("version", version)
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


    @SuppressWarnings("UnnecessaryLocalVariable")
    public String refreshToken(User user) {
        try {
            Instant now = Instant.now();
            Instant expired = now.plusSeconds(TokenType.REFRESH.lifetime);

            Algorithm algorithm = Algorithm.HMAC512(secret);
            String refreshToken = JWT.create()
                .withIssuer(issuer)
                .withClaim("id", user.getId())
                .withIssuedAt(now)
                .withNotBefore(now)
                .withExpiresAt(expired)
                .sign(algorithm);

            return refreshToken;
        } catch (
            JWTCreationException exception) {
            return "sovsem kapec";
        }
    }

    public Optional<DecodedJWT> verifyJWT(String token) {
        DecodedJWT decodedJWT;

        try {
            Algorithm algorithm = Algorithm.HMAC512(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(issuer)
                .withClaimPresence("id")
//                    .withClaimPresence("name")
//                    .withClaimPresence("avatar")
                .build();


            decodedJWT = verifier.verify(token);
            return Optional.of(decodedJWT);
        } catch (JWTVerificationException exception) {
            // Invalid signature/claims
            return Optional.empty();
        }
    }
}

