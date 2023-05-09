package com.sysmap.parrot.service.comment;

import com.sysmap.parrot.service.comment.request.CommentCreationRequest;
import com.sysmap.parrot.service.comment.request.CommentDeleteRequest;
import com.sysmap.parrot.service.comment.request.CommentEditRequest;
import com.sysmap.parrot.service.comment.request.CommentLikeOrDislikeRequest;
import com.sysmap.parrot.service.comment.response.GetCommentResponse;

import java.util.List;
import java.util.UUID;

public interface ICommentService {
    void createComment(CommentCreationRequest commentCreationRequest) throws Exception;
    List<GetCommentResponse> getComments(UUID id);
    void editComment(CommentEditRequest commentEditRequest) throws Exception;
    void deleteComment(CommentDeleteRequest commentDeleteRequest) throws Exception;
    void commentLike(CommentLikeOrDislikeRequest commentLikeOrDislikeRequest) throws Exception;
    void commentDislike(CommentLikeOrDislikeRequest commentLikeOrDislikeRequest) throws Exception;
}
