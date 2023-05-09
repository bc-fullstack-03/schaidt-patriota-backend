package com.sysmap.parrot.service.authentication.response;

import lombok.Data;

import java.util.UUID;

@Data
public class LoginResponse {
    public UUID userId;
    public String token;
}
