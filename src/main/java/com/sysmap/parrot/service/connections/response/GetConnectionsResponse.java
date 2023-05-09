package com.sysmap.parrot.service.connections.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetConnectionsResponse {
    public String profilePictureUrlUser;
    public String nameUser;
    public String email;
}
