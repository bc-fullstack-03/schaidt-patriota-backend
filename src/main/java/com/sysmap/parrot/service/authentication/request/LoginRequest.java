package com.sysmap.parrot.service.authentication.request;

import lombok.Data;

@Data
public class LoginRequest {
    public String email;
    public String password;
}
