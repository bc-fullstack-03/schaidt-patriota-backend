package com.sysmap.parrot.service.authentication;

import com.sysmap.parrot.service.authentication.request.LoginRequest;
import com.sysmap.parrot.service.authentication.response.LoginResponse;
import com.sysmap.parrot.service.security.IJwtService;
import com.sysmap.parrot.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements IAuthenticationService{

    @Autowired
    private IUserService iUserService;

    @Autowired
    private IJwtService iJwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public LoginResponse login(LoginRequest loginRequest) throws Exception {
        var user = iUserService.getUserByEmail(loginRequest.email);

        if(user == null){
            throw new Exception("User not found");
        }

        if(!passwordEncoder.matches(loginRequest.password, user.getPassword())){
            throw new Exception("Invalid credentials");
        }

        var token = iJwtService.generateToken(user.getId());

        var response = new LoginResponse();
        response.setUserId(user.getId());
        response.setToken(token);
        return response;
    }
}
