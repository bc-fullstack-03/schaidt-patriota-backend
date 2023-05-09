package com.sysmap.parrot.service.comment.request;

import lombok.Data;

import java.util.UUID;

@Data
public class CommentCreationRequest {
    public UUID userId;
    public UUID publicationId;
    public String text;
}
