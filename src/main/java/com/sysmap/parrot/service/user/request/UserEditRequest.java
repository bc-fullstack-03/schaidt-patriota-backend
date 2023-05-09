package com.sysmap.parrot.service.user.request;

import lombok.Data;

@Data
public class UserEditRequest {
    public String name;
    public String email;
    public String password;
    public String profilePictureUrl;

    public UserEditRequest(String name, String email, String password, String profilePictureUrl){
        this.name = name;
        this.email = email;
        this.password = password;
        this.profilePictureUrl = profilePictureUrl;
    }
}
