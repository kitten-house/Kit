package com.kit.backend.auth.service;

import com.kit.backend.auth.UsersDAO.UsersRepository;
import com.kit.backend.auth.entity.User;
import com.kit.backend.auth.model.GoogleAccount;
import com.kit.backend.auth.model.LoginRequestBody;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ServiceUserImp implements ServiceUser {

    private final GoogleService googleService;
    private final UsersRepository usersRepository;

    public ServiceUserImp(UsersRepository usersRepository, GoogleService googleService) {
        this.usersRepository = usersRepository;
        this.googleService = googleService;
    }

    @Override
    public User getOrSave(LoginRequestBody body) {

        GoogleAccount json =  googleService.googleRequest(body);
        Optional<User> user1 = usersRepository.findByGoogleId(json.id);
        if (user1.isPresent()) {
            return user1.get();
        }

        User user = null;
        user.setName(json.name);
        user.setGoogleId(json.id);
        user.setAvatar(json.picture);

        usersRepository.save(user);
        return user;
    }
}
