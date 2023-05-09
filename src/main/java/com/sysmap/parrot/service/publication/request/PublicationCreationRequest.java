package com.sysmap.parrot.service.publication.request;

import lombok.Data;

import java.util.UUID;

@Data
public class PublicationCreationRequest {
    public UUID userId;
    public String subtitle;
    //public String image;
}
