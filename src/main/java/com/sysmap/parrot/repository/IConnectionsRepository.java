package com.sysmap.parrot.repository;

import com.sysmap.parrot.model.entity.Connections;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface IConnectionsRepository extends MongoRepository<Connections, UUID> {
    Optional<Connections> findConnectionsByUserId(UUID userId);
}
