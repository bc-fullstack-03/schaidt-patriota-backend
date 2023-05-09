package com.sysmap.parrot.service.connections.request;

import lombok.Data;

import java.util.UUID;

@Data
public class RemoveConnectionsRequest {
    public UUID userId;
    public UUID followingOrFollowerId;
}
