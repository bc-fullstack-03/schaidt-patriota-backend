package com.sysmap.parrot.service.user.response;

import com.sysmap.parrot.model.entity.User;
import lombok.Data;

import java.util.UUID;

@Data
public class GetUsersResponse {
    public UUID id;
    public String name;
    public String email;
    private String profilePictureUrl;

    public GetUsersResponse(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
    }
}
