package com.sysmap.parrot.model.entity;

import com.sysmap.parrot.model.embedded.Comment;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class Publication {
    private UUID id;
    private UUID userId;
    private String subtitle;
    private String image;
    private LocalDateTime publicationDateAndTime;
    private List<UUID> likes;
    private List<Comment> comments;

    public Publication(UUID userId, String subtitle, String image){
        this.id = UUID.randomUUID();
        this.userId = userId;
        this.subtitle = subtitle;
        this.image = image;
        this.publicationDateAndTime = LocalDateTime.now();
        this.likes = new ArrayList<>();
        this.comments = new ArrayList<>();
    }
    
    public Publication(UUID userId, String subtitle){
        this.id = UUID.randomUUID();
        this.userId = userId;
        this.subtitle = subtitle;
        this.image = null;
        this.publicationDateAndTime = LocalDateTime.now();
        this.likes = new ArrayList<>();
        this.comments = new ArrayList<>();
    }

    public void addComment(Comment comment){
        this.comments.add(comment);
    }

    public void setComment(Comment comment){
        for(Comment x : this.comments){
            if (x.getId().equals(comment.getId())){
                x.setText(comment.getText());
                break;
            }
        }
    }

    public void deleteComment(UUID id){
        for(Comment comment : this.comments){
            if (comment.getId().equals(id)){
                this.comments.remove(comment);
                break;
            }
        }
    }

    public void like(UUID userId){
        if(!this.likes.contains(userId)){
            this.likes.add(userId);
        }
    }

    public void dislike(UUID id){
        this.likes.remove(id);
    }

    public void commentLike(UUID userId, UUID commentId){
        for(Comment comment : this.comments){
            if (comment.getId().equals(commentId)){
                comment.like(userId);
            }
        }
    }

    public void commentDislike(UUID userId, UUID commentId){
        for(Comment comment : this.comments){
            if (comment.getId().equals(commentId)){
                comment.dislike(userId);
            }
        }
    }
}
