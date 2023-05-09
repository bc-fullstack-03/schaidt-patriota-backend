package com.sysmap.parrot.service.comment;

import com.sysmap.parrot.model.embedded.Comment;
import com.sysmap.parrot.service.publication.IPublicationService;
import com.sysmap.parrot.service.comment.request.CommentCreationRequest;
import com.sysmap.parrot.service.comment.request.CommentDeleteRequest;
import com.sysmap.parrot.service.comment.request.CommentEditRequest;
import com.sysmap.parrot.service.comment.request.CommentLikeOrDislikeRequest;
import com.sysmap.parrot.service.comment.response.GetCommentResponse;
import com.sysmap.parrot.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CommentService implements  ICommentService{
    @Autowired
    IUserService iUserService;

    @Autowired
    IPublicationService iPublicationService;

    public void createComment(CommentCreationRequest commentCreationRequest) throws Exception {
        if(iUserService.findUserById(commentCreationRequest.userId) == null){
            throw new Exception("User not found");
        }
        var publication = iPublicationService.findById(commentCreationRequest.publicationId);
        if(publication == null){
            throw new Exception("Publication not found");
        }
        var comment = new Comment(commentCreationRequest.userId, commentCreationRequest.publicationId, commentCreationRequest.text);
        publication.addComment(comment);
        iPublicationService.savePublication(publication);
    }

    public List<GetCommentResponse> getComments(UUID id) {
        var publication = iPublicationService.findById(id);
        if (publication != null) {
            var comments = publication.getComments();
            if (comments != null && !comments.isEmpty()) {
                return comments.stream()
                        .map(comment -> {
                            var user = iUserService.findUserById(comment.getUserId());
                            return new GetCommentResponse(
                                    user.getProfilePictureUrl(),
                                    user.getName(),
                                    comment.getText(),
                                    comment.getCommentDateAndTime(),
                                    comment.getLikes()
                            );
                        })
                        .collect(Collectors.toList());
            }
        }
        return Collections.emptyList();
    }


    public void editComment(CommentEditRequest commentEditRequest) throws Exception {
        var publication = iPublicationService.findById(commentEditRequest.publicationId);
        if(publication != null){
            if (commentEditRequest.text == null && commentEditRequest.text.equals("")){
                throw new Exception("Invalid comment");
            }
            var comment = new Comment(commentEditRequest.commentId, commentEditRequest.text);
            publication.setComment(comment);
            iPublicationService.savePublication(publication);
            return;
        }
        throw new Exception("Publication not found");
    }

    public void deleteComment(CommentDeleteRequest commentDeleteRequest) throws Exception {
        var publication = iPublicationService.findById(commentDeleteRequest.publicationId);
        if(publication != null){
            publication.deleteComment(commentDeleteRequest.commentId);
            iPublicationService.savePublication(publication);
            return;
        }
        throw new Exception("Publication not found");
    }

    public void commentLike(CommentLikeOrDislikeRequest commentLikeOrDislikeRequest) throws Exception {
        var publication = iPublicationService.findById(commentLikeOrDislikeRequest.publicationId);
        if(publication != null){
            publication.commentLike(commentLikeOrDislikeRequest.userId, commentLikeOrDislikeRequest.commentId);
            iPublicationService.savePublication(publication);
            return;
        }
        throw new Exception("Publication not found");
    }

    public void commentDislike(CommentLikeOrDislikeRequest commentLikeOrDislikeRequest) throws Exception {
        var publication = iPublicationService.findById(commentLikeOrDislikeRequest.publicationId);
        if(publication != null){
            publication.commentDislike(commentLikeOrDislikeRequest.userId, commentLikeOrDislikeRequest.commentId);
            iPublicationService.savePublication(publication);
            return;
        }
        throw new Exception("Publication not found");
    }
}
