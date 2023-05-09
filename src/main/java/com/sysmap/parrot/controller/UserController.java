package com.sysmap.parrot.controller;

import com.sysmap.parrot.service.user.IUserService;
import com.sysmap.parrot.service.user.request.UserCreationRequest;
import com.sysmap.parrot.service.user.request.UserEditRequest;
import com.sysmap.parrot.service.user.response.GetUserByEmailResponse;
import com.sysmap.parrot.service.user.response.GetUsersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @PostMapping()//CRIAR USUARIO
    public ResponseEntity<String> createUser(@RequestBody UserCreationRequest userCreationRequest){
        try {
            iUserService.createUser(userCreationRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PostMapping("/picture/upload")
    public ResponseEntity<String> uploadProfilePicture(@RequestParam("picture") MultipartFile picture) {
    	try {
            iUserService.uploadProfilePicture(picture);
            return ResponseEntity.status(HttpStatus.OK).body("Picture updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{email}")//PEGAR USUARIO POR EMAIL
    public ResponseEntity<GetUserByEmailResponse> getUser(@PathVariable("email") String email){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(iUserService.getUser(email));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping()//PEGAR TODOS OS USUARIOS
    public ResponseEntity<List<GetUsersResponse>> getUsers(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(iUserService.getUsers());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PatchMapping("/{id}")//EDITAR USUARIO POR ID
    public ResponseEntity<String> editUser(@PathVariable("id") UUID id, @RequestBody UserEditRequest userEditRequest){
        try {
            iUserService.editUser(id, userEditRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body("User successfully edited");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")//DELETAR USUARIO POR ID
    public ResponseEntity<String> deleteUser(@PathVariable("id") UUID id){
        try {
            iUserService.deleteUser(id);
            return ResponseEntity.status(HttpStatus.CREATED).body("User deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
