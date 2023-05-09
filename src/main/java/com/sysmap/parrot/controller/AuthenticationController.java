package com.sysmap.parrot.controller;

import com.sysmap.parrot.service.authentication.IAuthenticationService;
import com.sysmap.parrot.service.authentication.request.LoginRequest;
import com.sysmap.parrot.service.authentication.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/authentication")
public class AuthenticationController {

    @Autowired
    IAuthenticationService iAuthenticationService;

    //Login
    @PostMapping()
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
    	try {
            return ResponseEntity.status(HttpStatus.OK).body(iAuthenticationService.login(loginRequest));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}
