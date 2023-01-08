package com.kit.backend.auth.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.kit.backend.auth.Tokens.JWTTokens;
import com.kit.backend.auth.UsersDAO.UsersRepository;
import com.kit.backend.auth.entity.User;
import com.kit.backend.auth.model.AuthResponseBody;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImp {

    private final UsersRepository repository;
    private final JWTTokens jwtToken;

    public AuthServiceImp(UsersRepository repository, JWTTokens jwtToken) {
        this.repository = repository;
        this.jwtToken = jwtToken;
    }

    public AuthResponseBody generateTokens(User user){
        String acToken = jwtToken.accessToken(user);
        String reToken = jwtToken.refreshToken(user);
        return new AuthResponseBody(acToken, reToken);
    }

    public Optional<User> getUserByToken(String token) {
        Optional<DecodedJWT> decodedJWT = jwtToken.verifyJWT(token);
        if (decodedJWT.isEmpty()) {
            return Optional.empty();
        }
        DecodedJWT jwt = decodedJWT.get();

        User user = repository.getReferenceById(jwt.getClaim("id").asLong());
        return Optional.of(user);
    }
}
