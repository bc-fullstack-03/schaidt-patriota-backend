package com.sysmap.parrot.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class User{
    private UUID id;
    private String name;
    private String email;
    private String password;
    private String profilePictureUrl;

    public User(String name, String email){
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
    }

}
