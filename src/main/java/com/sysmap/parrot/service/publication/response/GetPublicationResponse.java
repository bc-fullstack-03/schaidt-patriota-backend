package com.sysmap.parrot.service.publication.response;

import com.sysmap.parrot.model.embedded.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class GetPublicationResponse {
    public String profilePictureUrlUser;
    public String nameUser;
    public UUID publicationId;
    public String subtitle;
    public String image;
    public LocalDateTime publicationDateAndTime;
    public List<UUID> publicationLike;
    public List<Comment> comment;
}
