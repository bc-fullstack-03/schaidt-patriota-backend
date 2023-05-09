package com.sysmap.parrot.service.comment.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class GetCommentResponse {
    public String profilePictureUrlUser;
    public String nameUser;
    public String text;
    public LocalDateTime commentDateAndTime;
    public List<UUID> commentLike;
}
