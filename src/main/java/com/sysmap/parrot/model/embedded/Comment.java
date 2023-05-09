package com.sysmap.parrot.model.embedded;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class Comment {
    private UUID id;
    private UUID userId;
    private UUID publicationId;
    private String text;
    private LocalDateTime commentDateAndTime;
    private List<UUID> likes;

    public Comment(UUID userId, UUID publicationId, String text){
        this.id = UUID.randomUUID();
        this.userId = userId;
        this.publicationId = publicationId;
        this.text = text;
        this.commentDateAndTime = LocalDateTime.now();
        this.likes = new ArrayList<>();
    }

    public Comment(UUID id, String text){
        this.id = id;
        this.text = text;
    }

    public void like(UUID userId){
        if(!this.likes.contains(userId)){
            this.likes.add(userId);
        }
    }

    public void dislike(UUID id){
        this.likes.remove(id);
    }
}
