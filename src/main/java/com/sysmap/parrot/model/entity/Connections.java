package com.sysmap.parrot.model.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class Connections {
    private UUID id;
    private UUID userId;
    private List<UUID> following = new ArrayList<>();//Seguindo
    private List<UUID> followers = new ArrayList<>();//Seguidores

    public Connections(UUID userId){
        this.id = UUID.randomUUID();
        this.userId = userId;
        this.following = new ArrayList<>();
        this.followers = new ArrayList<>();
    }

    public void addFollowing(UUID id){
        if(!this.following.contains(id)){
            this.following.add(id);
        }
    }

    public void removeFollowing(UUID id){
        this.following.remove(id);
    }

    public void addFollowers(UUID id){
        if(!this.followers.contains(id)){
            this.followers.add(id);
        }
    }

    public void removeFollowers(UUID id){
        this.followers.remove(id);
    }
}
