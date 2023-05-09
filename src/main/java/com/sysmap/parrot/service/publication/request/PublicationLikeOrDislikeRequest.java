package com.sysmap.parrot.service.publication.request;

import lombok.Data;

import java.util.UUID;

@Data
public class PublicationLikeOrDislikeRequest {
    public UUID publicationId;
    public UUID userId;
}
