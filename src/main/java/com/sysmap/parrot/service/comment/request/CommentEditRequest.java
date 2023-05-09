package com.sysmap.parrot.service.comment.request;

import lombok.Data;

import java.util.UUID;

@Data
public class CommentEditRequest {
    public UUID publicationId;
    public UUID commentId;
    public String text;
}
