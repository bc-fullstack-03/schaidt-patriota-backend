package com.sysmap.parrot.service.user;

import com.sysmap.parrot.model.entity.User;
import com.sysmap.parrot.repository.IUserRepository;
import com.sysmap.parrot.service.fileupload.IFileUploadService;
import com.sysmap.parrot.service.user.request.UserCreationRequest;
import com.sysmap.parrot.service.user.request.UserEditRequest;
import com.sysmap.parrot.service.user.response.GetUserByEmailResponse;
import com.sysmap.parrot.service.user.response.GetUsersResponse;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService{

    @Autowired
    private IUserRepository iUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IFileUploadService iFileUploadService;

    public void createUser(UserCreationRequest userCreationRequest) throws Exception {
        if(isValidEmail(userCreationRequest.email) && getUser(userCreationRequest.email) == null){
            var user = new User(userCreationRequest.name, userCreationRequest.email);
            try {
                user.setPassword(passwordEncoder.encode(userCreationRequest.password));
                iUserRepository.save(user);
                return;
            } catch (Exception e){
                e.getMessage();
                return;
            }
        }
        throw new Exception("Invalid or already used email");
    }

    public GetUserByEmailResponse getUser(String email){
        var user = iUserRepository.findUserByEmail(email).orElse(null);
        if(user != null){
            return new GetUserByEmailResponse(user.getId(), user.getName(), user.getEmail(), user.getProfilePictureUrl());
        }
        return null;
    }

    public void editUser(UUID id, UserEditRequest userEditRequest) throws Exception {
        var user = iUserRepository.findById(id).orElse(null);
        if(user != null){
            if(userEditRequest.email != null && !userEditRequest.email.equals("")){
                if (isValidEmail(userEditRequest.email) && getUser(userEditRequest.email) == null){
                    user.setEmail(userEditRequest.email);
                }
                throw new Exception("Invalid or already used email");
            }

            if(userEditRequest.name != null && !userEditRequest.name.equals("")){
                user.setName(userEditRequest.name);
            }

            if (userEditRequest.password != null && !userEditRequest.password.equals("")){
                user.setPassword(userEditRequest.password);
            }

            if(userEditRequest.profilePictureUrl != null && !userEditRequest.name.equals("")){
                user.setProfilePictureUrl(userEditRequest.profilePictureUrl);
            }

            try {
                iUserRepository.save(user);
                return;
            } catch (Exception e){
                throw new Exception(e.getMessage());
            }
        }
        throw new Exception("User not found");
    }

    public Boolean isValidEmail(String email){
        EmailValidator validator = EmailValidator.getInstance();
       return validator.isValid(email);
    }

    public void deleteUser(UUID id) throws Exception {
        if(iUserRepository.findById(id).isEmpty()){
            throw new Exception("User not found");
        }
        try {
            iUserRepository.deleteById(id);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public List<GetUsersResponse> getUsers() {
        List<User> users = iUserRepository.findAll();
        if (users != null && !users.isEmpty()) {
            return users.stream()
                    .map(GetUsersResponse::new)
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

    public User findUserById(UUID id){
        return iUserRepository.findById(id).orElse(null);
    }

    public User getUserByEmail(String email){
        return iUserRepository.findUserByEmail(email).orElse(null);
    }

    public void uploadProfilePicture(MultipartFile picture) throws Exception {
        var user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        var pictureUrl = "";

        try {
            var fileName = user.getId() + "." + picture.getOriginalFilename().substring(picture.getOriginalFilename().lastIndexOf(".") + 1);
            pictureUrl = iFileUploadService.upload(picture, fileName);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        user.setProfilePictureUrl(pictureUrl);
        iUserRepository.save(user);
    }
}
