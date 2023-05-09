package com.sysmap.parrot.controller;

import com.sysmap.parrot.service.publication.IPublicationService;
import com.sysmap.parrot.service.publication.request.PublicationCreationRequest;
import com.sysmap.parrot.service.publication.request.PublicationEditRequest;
import com.sysmap.parrot.service.publication.request.PublicationLikeOrDislikeRequest;
import com.sysmap.parrot.service.publication.response.GetPublicationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/publication")
public class PublicationController {

    @Autowired
    IPublicationService iPublicationService;

    @PostMapping()//CRIAR PUBLICAÇÃO
    public ResponseEntity<String> createPublication(@RequestParam("image")MultipartFile image, @RequestBody PublicationCreationRequest publicationCreationRequest){
        try {
            iPublicationService.createPublucation(image, publicationCreationRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body("Post created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/like")//DA LIKE
    public ResponseEntity<String> addLikePublication(@RequestBody PublicationLikeOrDislikeRequest publicationLikeOrDislikeRequest){
        try {
            iPublicationService.publicationLike(publicationLikeOrDislikeRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body("Publication like successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")//PEGAR PUBLICAÇÃO POR ID
    public ResponseEntity<GetPublicationResponse> getPublication(@PathVariable("id")UUID id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(iPublicationService.getPublication(id));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping()//PEGAR TODAS AS PUBLICAÇÕES
    public ResponseEntity<List<GetPublicationResponse>> getPublications(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(iPublicationService.getPublications());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/user/{userId}")//PEGAR TODAS AS PUBLICAÇÕES DO USUÁRIO
    public ResponseEntity<List<GetPublicationResponse>> getUserPublications(@PathVariable("userId") UUID userId){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(iPublicationService.getUserPublications(userId));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/user/following/{userId}")
    public ResponseEntity<List<GetPublicationResponse>> getUserFollowingPublications(@PathVariable("userId") UUID userId){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(iPublicationService.getUserFollowingPublications(userId));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PatchMapping()//EDITAR PUBLICAÇÃO POR ID
    public ResponseEntity<String> editPublication(@RequestParam("image")MultipartFile image, @RequestBody PublicationEditRequest publicationEditRequest){
        try {
            iPublicationService.editPublication(image, publicationEditRequest);
            return ResponseEntity.status(HttpStatus.OK).body("Post edited successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")//DELETAR PUBLICAÇÃO POR ID
    public ResponseEntity<String> deletePublication(@PathVariable("id") UUID id){
        try {
            iPublicationService.deletePublication(id);
            return ResponseEntity.status(HttpStatus.OK).body("Post deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/dislike")//TIRAR LIKE
    public ResponseEntity<String> deleteLikePublication(@RequestBody PublicationLikeOrDislikeRequest publicationLikeOrDislikeRequest){
        try {
            iPublicationService.publicationDislike(publicationLikeOrDislikeRequest);
            return ResponseEntity.status(HttpStatus.OK).body("Publication dislike successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
