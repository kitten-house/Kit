package com.kit.backend.auth.service;

import com.kit.backend.auth.entity.Users;
import com.kit.backend.auth.model.LoginRequestBody;

public interface GoogleService {
    public Users googleRequest(LoginRequestBody body);
}
