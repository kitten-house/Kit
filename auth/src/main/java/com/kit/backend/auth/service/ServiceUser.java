package com.kit.backend.auth.service;

import com.kit.backend.auth.controller.AuthController;
import com.kit.backend.auth.entity.Users;
import com.kit.backend.auth.model.ForJSON;

import java.util.List;

public interface ServiceUser {
    public Users addUser (ForJSON json);
}
