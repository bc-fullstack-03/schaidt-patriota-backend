package com.sysmap.parrot.service.connections;

import com.sysmap.parrot.model.entity.Connections;
import com.sysmap.parrot.repository.IConnectionsRepository;
import com.sysmap.parrot.service.connections.request.AddConnectionsRequest;
import com.sysmap.parrot.service.connections.request.RemoveConnectionsRequest;
import com.sysmap.parrot.service.connections.response.GetConnectionsResponse;
import com.sysmap.parrot.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ConnectionsService implements IConnectionsService{

    @Autowired
    IConnectionsRepository iConnectionsRepository;

    @Autowired
    IUserService iUserService;

    public void addFollowing(AddConnectionsRequest addConnectionsRequest) throws Exception {
        if(iUserService.findUserById(addConnectionsRequest.userId) == null){
            throw new Exception("User not found");
        }

        if(iUserService.findUserById(addConnectionsRequest.followingId) == null){
            throw new Exception("Following not found");
        }

        try {
            Connections connections1 = iConnectionsRepository.findConnectionsByUserId(addConnectionsRequest.userId).orElseGet(() -> new Connections(addConnectionsRequest.userId));
            connections1.addFollowing(addConnectionsRequest.followingId);
            iConnectionsRepository.save(connections1);

            Connections connections2 = iConnectionsRepository.findConnectionsByUserId(addConnectionsRequest.followingId).orElseGet(() -> new Connections(addConnectionsRequest.followingId));
            connections2.addFollowers(addConnectionsRequest.userId);
            iConnectionsRepository.save(connections2);

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<GetConnectionsResponse> getFollowing(UUID id) {
        var connections = iConnectionsRepository.findConnectionsByUserId(id).orElse(null);
        if (connections != null) {
            var following = connections.getFollowing();
            if (following != null && !following.isEmpty()) {
                return following.stream()
                        .map(followingId -> {
                            var user = iUserService.findUserById(followingId);
                            return new GetConnectionsResponse(
                                    user.getProfilePictureUrl(),
                                    user.getName(),
                                    user.getEmail()
                            );
                        })
                        .collect(Collectors.toList());
            }
        }
        return Collections.emptyList();
    }


    public List<GetConnectionsResponse> getFollowers(UUID id) {
        var connections = iConnectionsRepository.findConnectionsByUserId(id).orElse(null);
        if (connections != null) {
            var followers = connections.getFollowers();
            if (followers != null && !followers.isEmpty()) {
                return followers.stream()
                        .map(followersId -> {
                            var user = iUserService.findUserById(followersId);
                            return new GetConnectionsResponse(
                                    user.getProfilePictureUrl(),
                                    user.getName(),
                                    user.getEmail()
                            );
                        })
                        .collect(Collectors.toList());
            }
        }
        return Collections.emptyList();
    }

    public void removeFollowing(RemoveConnectionsRequest removeConnectionsRequest) throws Exception {
        if(iUserService.findUserById(removeConnectionsRequest.userId) == null || iUserService.findUserById(removeConnectionsRequest.followingOrFollowerId) == null){
            throw new Exception("User not found/Following not found");
        }

        try {
            Connections connections1 = iConnectionsRepository.findConnectionsByUserId(removeConnectionsRequest.userId).orElseGet(() -> new Connections(removeConnectionsRequest.userId));
            connections1.removeFollowing(removeConnectionsRequest.followingOrFollowerId);
            iConnectionsRepository.save(connections1);

            Connections connections2 = iConnectionsRepository.findConnectionsByUserId(removeConnectionsRequest.followingOrFollowerId).orElseGet(() -> new Connections(removeConnectionsRequest.followingOrFollowerId));
            connections2.removeFollowers(removeConnectionsRequest.userId);
            iConnectionsRepository.save(connections2);

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void removeFollowers(RemoveConnectionsRequest removeConnectionsRequest) throws Exception {
        if(iUserService.findUserById(removeConnectionsRequest.userId) == null || iUserService.findUserById(removeConnectionsRequest.followingOrFollowerId) == null){
            throw new Exception("User not found/Followers not found");
        }

        try {
            Connections connections1 = iConnectionsRepository.findConnectionsByUserId(removeConnectionsRequest.userId).orElseGet(() -> new Connections(removeConnectionsRequest.userId));
            connections1.removeFollowers(removeConnectionsRequest.followingOrFollowerId);
            iConnectionsRepository.save(connections1);

            Connections connections2 = iConnectionsRepository.findConnectionsByUserId(removeConnectionsRequest.followingOrFollowerId).orElseGet(() -> new Connections(removeConnectionsRequest.followingOrFollowerId));
            connections2.removeFollowing(removeConnectionsRequest.userId);
            iConnectionsRepository.save(connections2);

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<UUID> getFollowingId(UUID userId){
        var connections = iConnectionsRepository.findConnectionsByUserId(userId).orElse(null);
        if (connections != null) {
            return connections.getFollowing();
        }
        return Collections.emptyList();
    }
}
