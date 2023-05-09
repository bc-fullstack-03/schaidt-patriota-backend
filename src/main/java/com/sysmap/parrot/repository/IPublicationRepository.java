package com.sysmap.parrot.repository;

import com.sysmap.parrot.model.entity.Publication;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IPublicationRepository extends MongoRepository<Publication, UUID> {
    Optional<List<Publication>> findAllPublicationByUserId(UUID userId);
}
