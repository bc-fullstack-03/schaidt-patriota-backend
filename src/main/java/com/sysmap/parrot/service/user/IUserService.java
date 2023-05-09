package com.sysmap.parrot.service.user;

import com.sysmap.parrot.model.entity.User;
import com.sysmap.parrot.service.user.request.UserCreationRequest;
import com.sysmap.parrot.service.user.request.UserEditRequest;
import com.sysmap.parrot.service.user.response.GetUserByEmailResponse;
import com.sysmap.parrot.service.user.response.GetUsersResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface IUserService {
    void createUser(UserCreationRequest userCreationRequest) throws Exception;
    GetUserByEmailResponse getUser(String email);
    void editUser(UUID id, UserEditRequest userEditRequest) throws Exception;
    Boolean isValidEmail(String email);
    void deleteUser(UUID id) throws Exception;
    List<GetUsersResponse> getUsers();
    User findUserById(UUID id);
    User getUserByEmail(String email);
    void uploadProfilePicture(MultipartFile picture) throws Exception;
}
