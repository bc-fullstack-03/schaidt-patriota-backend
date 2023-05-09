package com.sysmap.parrot.service.user.response;

import lombok.Data;

import java.util.UUID;

@Data
public class GetUserByEmailResponse {
    public UUID id;
    public String name;
    public String email;
    private String profilePictureUrl;

    public GetUserByEmailResponse(UUID id, String name, String email, String profilePictureUrl){
        this.id = id;
        this.name = name;
        this.email = email;
        this.profilePictureUrl = profilePictureUrl;
    }
}
