package com.kit.backend.auth.service;

import com.kit.backend.auth.UsersDAO.UsersRepository;
import com.kit.backend.auth.entity.Users;
import com.kit.backend.auth.model.ForJSON;
import org.springframework.stereotype.Service;



@Service
public class ServiceUserImp implements ServiceUser {
    private UsersRepository usersRepository;
    @Override
    public Users addUser(ForJSON json) {
        Users user = null;
        user.setName(json.name);
        user.setGoogleId(json.id);
        user.setAvatar(json.avatarUrl);
        usersRepository.save(user);
        return user;
    }

}
