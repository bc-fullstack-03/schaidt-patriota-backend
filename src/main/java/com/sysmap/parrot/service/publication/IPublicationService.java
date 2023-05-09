package com.sysmap.parrot.service.publication;

import com.sysmap.parrot.model.entity.Publication;
import com.sysmap.parrot.service.publication.request.PublicationCreationRequest;
import com.sysmap.parrot.service.publication.request.PublicationEditRequest;
import com.sysmap.parrot.service.publication.request.PublicationLikeOrDislikeRequest;
import com.sysmap.parrot.service.publication.response.GetPublicationResponse;

import java.util.List;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public interface IPublicationService {
    //Publication
    void createPublucation(MultipartFile image, PublicationCreationRequest publicationCreationRequest) throws Exception;
    GetPublicationResponse getPublication(UUID id);
    void editPublication(MultipartFile image, PublicationEditRequest publicationEditRequest) throws Exception;
    void deletePublication(UUID id) throws Exception;
    List<GetPublicationResponse> getPublications();
    void publicationLike(PublicationLikeOrDislikeRequest likePublicationRequest) throws Exception;
    void publicationDislike(PublicationLikeOrDislikeRequest likePublicationRequest) throws Exception;
    List<GetPublicationResponse> getUserPublications(UUID userId) throws Exception;
    List<GetPublicationResponse> getUserFollowingPublications(UUID userId) throws Exception;

    //Comentário
    Publication findById(UUID id);
    void savePublication(Publication publication) throws Exception;
}
