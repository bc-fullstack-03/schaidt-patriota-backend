package com.sysmap.parrot.controller;

import com.sysmap.parrot.service.connections.IConnectionsService;
import com.sysmap.parrot.service.connections.request.AddConnectionsRequest;
import com.sysmap.parrot.service.connections.request.RemoveConnectionsRequest;
import com.sysmap.parrot.service.connections.response.GetConnectionsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/connections")
public class ConnectionsController {

    @Autowired
    IConnectionsService iConnectionsService;

    @PostMapping()//ADICIONAR SEGUIDOR
    public ResponseEntity<String> addFollowing(@RequestBody AddConnectionsRequest addConnectionsRequest){
        try {
            iConnectionsService.addFollowing(addConnectionsRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body("successfully following");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/following/{id}")
    public ResponseEntity<List<GetConnectionsResponse>> getFollowing(@PathVariable("id") UUID id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(iConnectionsService.getFollowing(id));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/followers/{id}")
    public ResponseEntity<List<GetConnectionsResponse>> getFollowers(@PathVariable("id") UUID id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(iConnectionsService.getFollowers(id));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

   @DeleteMapping("/following")
    public ResponseEntity<String> removeFollowing(@RequestBody RemoveConnectionsRequest removeConnectionsRequest){
       try {
           iConnectionsService.removeFollowing(removeConnectionsRequest);
           return ResponseEntity.status(HttpStatus.CREATED).body("Following remove successfully");
       } catch (Exception e){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
       }
    }

    @DeleteMapping("/followers")
    public ResponseEntity<String> removeFollowers(@RequestBody RemoveConnectionsRequest removeConnectionsRequest){
        try {
            iConnectionsService.removeFollowers(removeConnectionsRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body("Followers remove successfully");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
