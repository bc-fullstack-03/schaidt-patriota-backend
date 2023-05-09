package com.sysmap.parrot.service.connections.request;

import lombok.Data;

import java.util.UUID;

@Data
public class AddConnectionsRequest {
    public UUID userId;
    public UUID followingId;
}
