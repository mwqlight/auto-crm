package com.stellarcrm.service.auth;

import com.stellarcrm.dto.auth.LoginRequest;
import com.stellarcrm.dto.auth.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest request);
}