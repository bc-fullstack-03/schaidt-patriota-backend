package com.sysmap.parrot.controller;

import com.sysmap.parrot.service.comment.ICommentService;
import com.sysmap.parrot.service.comment.request.CommentCreationRequest;
import com.sysmap.parrot.service.comment.request.CommentDeleteRequest;
import com.sysmap.parrot.service.comment.request.CommentEditRequest;
import com.sysmap.parrot.service.comment.request.CommentLikeOrDislikeRequest;
import com.sysmap.parrot.service.comment.response.GetCommentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/publication/comment")
public class CommentController {

    @Autowired
    ICommentService iCommentService;

    @PostMapping()//CRIAR COMENTARIO
    public ResponseEntity<String> createComment(@RequestBody CommentCreationRequest commentCreationRequest){
        try {
            iCommentService.createComment(commentCreationRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body("Comment created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/like")//DA LIKE
    public ResponseEntity<String> addLikeComment(@RequestBody CommentLikeOrDislikeRequest commentLikeOrDislikeRequest){
        try {
            iCommentService.commentLike(commentLikeOrDislikeRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body("Comment like successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{publicationId}")//PEGAR TODOS OS COMENTARIO DA PUBLICACAO DO ID INSERIDO
    public ResponseEntity<List<GetCommentResponse>> getComment(@PathVariable("publicationId") UUID publicationId){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(iCommentService.getComments(publicationId));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PatchMapping()//EDITAR COMENTARIO
    public ResponseEntity<String> editComment(@RequestBody CommentEditRequest commentEditRequest){
        try {
            iCommentService.editComment(commentEditRequest);
            return ResponseEntity.status(HttpStatus.OK).body("Comment edited successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping()//DELETAR COMENTARIO
    public ResponseEntity<String> deleteComment(@RequestBody CommentDeleteRequest commentDeleteRequest){
        try {
            iCommentService.deleteComment(commentDeleteRequest);
            return ResponseEntity.status(HttpStatus.OK).body("Comment deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/dislike")//TIRAR LIKE DO COMENTARIO
    public ResponseEntity<String> removeLikeComment(@RequestBody CommentLikeOrDislikeRequest commentLikeOrDislikeRequest){
        try {
            iCommentService.commentDislike(commentLikeOrDislikeRequest);
            return ResponseEntity.status(HttpStatus.OK).body("Comment dislike successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
