package com.kit.backend.auth.service;

import com.kit.backend.auth.model.GoogleAccount;
import com.kit.backend.auth.model.LoginRequestBody;

public interface GoogleService {
    GoogleAccount googleRequest(LoginRequestBody body);
}
