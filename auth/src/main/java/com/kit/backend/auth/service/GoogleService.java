package com.kit.backend.auth.service;

import com.kit.backend.auth.model.ForJSON;
import com.kit.backend.auth.model.LoginRequestBody;

public interface GoogleService {
    ForJSON googleRequest(LoginRequestBody body);
}
