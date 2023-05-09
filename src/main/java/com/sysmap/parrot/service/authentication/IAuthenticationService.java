package com.sysmap.parrot.service.authentication;

import com.sysmap.parrot.service.authentication.request.LoginRequest;
import com.sysmap.parrot.service.authentication.response.LoginResponse;

public interface IAuthenticationService {
    LoginResponse login(LoginRequest loginRequest) throws Exception;
}
