package com.sysmap.parrot.service.connections;

import com.sysmap.parrot.service.connections.request.AddConnectionsRequest;
import com.sysmap.parrot.service.connections.request.RemoveConnectionsRequest;
import com.sysmap.parrot.service.connections.response.GetConnectionsResponse;

import java.util.List;
import java.util.UUID;

public interface IConnectionsService {
    void addFollowing(AddConnectionsRequest addConnectionsRequest) throws Exception;
    List<GetConnectionsResponse> getFollowing(UUID id);
    List<GetConnectionsResponse> getFollowers(UUID id);
    void removeFollowing(RemoveConnectionsRequest removeConnectionsRequest) throws Exception;
    void removeFollowers(RemoveConnectionsRequest removeConnectionsRequest) throws Exception;
    List<UUID> getFollowingId(UUID userId);
}
