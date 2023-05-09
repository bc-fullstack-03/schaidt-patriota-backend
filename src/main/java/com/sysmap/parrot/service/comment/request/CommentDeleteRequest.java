package com.sysmap.parrot.service.comment.request;

import lombok.Data;

import java.util.UUID;

@Data
public class CommentDeleteRequest {
    public UUID publicationId;
    public UUID commentId;
}
