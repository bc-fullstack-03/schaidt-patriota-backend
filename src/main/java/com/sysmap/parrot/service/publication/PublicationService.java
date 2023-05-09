package com.sysmap.parrot.service.publication;

import com.sysmap.parrot.model.entity.Publication;
import com.sysmap.parrot.repository.IPublicationRepository;
import com.sysmap.parrot.service.connections.IConnectionsService;
import com.sysmap.parrot.service.fileupload.IFileUploadService;
import com.sysmap.parrot.service.publication.request.PublicationCreationRequest;
import com.sysmap.parrot.service.publication.request.PublicationEditRequest;
import com.sysmap.parrot.service.publication.request.PublicationLikeOrDislikeRequest;
import com.sysmap.parrot.service.publication.response.GetPublicationResponse;
import com.sysmap.parrot.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PublicationService implements IPublicationService{

    @Autowired
    IPublicationRepository iPublicationRepository;

    @Autowired
    IUserService iUserService;

    @Autowired
    IConnectionsService iConnectionsService;
    
    @Autowired
    private IFileUploadService iFileUploadService;

    public void createPublucation(MultipartFile image,PublicationCreationRequest publicationCreationRequest) throws Exception {
        try{
        	if(iUserService.findUserById(publicationCreationRequest.userId) == null){
                throw  new Exception("User not found");
            }
        	
        	var publication = new Publication(publicationCreationRequest.userId, publicationCreationRequest.subtitle);
            
        	publication.setImage(getImage(publication.getId(), image));
            iPublicationRepository.save(publication);
        } catch (Exception e){
            throw  new Exception(e.getMessage());
        }
    }

    public GetPublicationResponse getPublication(UUID id) {
        var publication = iPublicationRepository.findById(id).orElse(null);
        if(publication != null){
            var user = iUserService.findUserById(publication.getUserId());
            return new GetPublicationResponse(user.getProfilePictureUrl(), user.getName(), publication.getId(), publication.getSubtitle(), publication.getImage(), publication.getPublicationDateAndTime(), publication.getLikes(), publication.getComments());
        }
        return null;
    }

    public void editPublication(MultipartFile image, PublicationEditRequest publicationEditRequest) throws Exception {
        var publication = iPublicationRepository.findById(publicationEditRequest.id).orElse(null);
        if(publication != null){
            if(publicationEditRequest.subtitle != null){
                publication.setSubtitle(publicationEditRequest.subtitle);
            }

            if(image != null){
            	try {
            		publication.setImage(getImage(publication.getId(), image));
            	} catch (Exception e) {
            		throw  new Exception(e.getMessage());
				}
            }

            try {
                iPublicationRepository.save(publication);
                return;
            } catch (Exception e){
                throw  new Exception(e.getMessage());
            }
        }
        throw  new Exception("Publication not found");
    }

    public void deletePublication(UUID id) throws Exception {
        if(iPublicationRepository.findById(id).isEmpty()){
            throw  new Exception("Publication not found");
        }

        try {
            iPublicationRepository.deleteById(id);
        } catch (Exception e){
            throw  new Exception(e.getMessage());
        }
    }

    public List<GetPublicationResponse> getPublications(){
        return listGetPublicationResponse(iPublicationRepository.findAll());
    }

    public List<GetPublicationResponse> getUserPublications(UUID userId) throws Exception {
        var user = iUserService.findUserById(userId);
        if(user == null){
            throw  new Exception("User not found");
        }
        return listGetPublicationResponse(iPublicationRepository.findAllPublicationByUserId(user.getId()).orElse(null));
    }

    public List<GetPublicationResponse> getUserFollowingPublications(UUID userId) throws Exception {
        var user = iUserService.findUserById(userId);
        if (user == null) {
            throw  new Exception("Publication not found");
        }

        var getPublicationResponse = listGetPublicationResponse(iPublicationRepository.findAllPublicationByUserId(userId).orElseGet(Collections::emptyList));
        var following = iConnectionsService.getFollowingId(userId);

        if (following != null && !following.isEmpty()) {
            var publications = findPublicationsByFollowingIds(following);
            getPublicationResponse.addAll(listGetPublicationResponse(publications));
        }
        getPublicationResponse.sort(Comparator.comparing(GetPublicationResponse::getPublicationDateAndTime).reversed());
        return getPublicationResponse;
    }

    private List<Publication> findPublicationsByFollowingIds(List<UUID> followingIds) {
        List<Publication> publications = new ArrayList<>();
        for (UUID followingId : followingIds) {
            var publicationsForFollowing = iPublicationRepository.findAllPublicationByUserId(followingId).orElseGet(Collections::emptyList);
            publications.addAll(publicationsForFollowing);
        }
        return publications;
    }

    private List<GetPublicationResponse> listGetPublicationResponse(List<Publication> publications){
        if (publications != null && !publications.isEmpty()){
            return publications.stream()
                    .map(publication -> {
                        var user = iUserService.findUserById(publication.getUserId());
                        return new GetPublicationResponse(
                                user.getProfilePictureUrl(),
                                user.getName(),
                                publication.getId(),
                                publication.getSubtitle(),
                                publication.getImage(),
                                publication.getPublicationDateAndTime(),
                                publication.getLikes(),
                                publication.getComments()
                        );
                    })
                    .sorted(Comparator.comparing(GetPublicationResponse::getPublicationDateAndTime).reversed())
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public Publication findById(UUID id){
        return iPublicationRepository.findById(id).orElse(null);
    }

    public void savePublication(Publication publication) throws Exception {
        try {
            iPublicationRepository.save(publication);
        } catch (Exception e) {
            throw  new Exception(e.getMessage());
        }
    }

    public void publicationLike(PublicationLikeOrDislikeRequest publicationLikeOrDislikeRequest) throws Exception {
        var publication = iPublicationRepository.findById(publicationLikeOrDislikeRequest.publicationId).orElse(null);
        if(publication != null){
            publication.like(publicationLikeOrDislikeRequest.userId);
            savePublication(publication);
            return;
        }
        throw  new Exception("Publication not found");
    }

    public void publicationDislike(PublicationLikeOrDislikeRequest publicationLikeOrDislikeRequest) throws Exception {
        var publication = iPublicationRepository.findById(publicationLikeOrDislikeRequest.publicationId).orElse(null);
        if(publication != null){
            publication.dislike(publicationLikeOrDislikeRequest.userId);
            savePublication(publication);
            return;
        }
        throw  new Exception("Publication not found");
    }
    
    private String getImage(UUID publicationId, MultipartFile image) throws Exception {
    	try {
    		var fileName = publicationId + "." + image.getOriginalFilename().substring(image.getOriginalFilename().lastIndexOf(".") + 1);
    		fileName = iFileUploadService.upload(image, fileName);
    		return fileName;
    	} catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
















