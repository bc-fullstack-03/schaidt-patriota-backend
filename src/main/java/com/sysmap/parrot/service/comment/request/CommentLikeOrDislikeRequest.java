package com.sysmap.parrot.service.comment.request;

import lombok.Data;

import java.util.UUID;

@Data
public class CommentLikeOrDislikeRequest {
    public UUID publicationId;
    public UUID commentId;
    public UUID userId;
}
