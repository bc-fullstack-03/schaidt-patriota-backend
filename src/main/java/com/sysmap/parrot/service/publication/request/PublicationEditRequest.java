package com.sysmap.parrot.service.publication.request;

import java.util.UUID;

import lombok.Data;

@Data
public class PublicationEditRequest {
	public UUID id;
    public String subtitle;
    //public String image;
}
