package com.kit.backend.auth.service;

import com.kit.backend.auth.entity.User;
import com.kit.backend.auth.model.LoginRequestBody;

public interface ServiceUser {
    User getOrSave(LoginRequestBody body);
}
