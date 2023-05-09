package com.sysmap.parrot.service.user.request;

import lombok.Data;

@Data
public class UserCreationRequest {
    public String name;
    public String email;
    public String password;
}
