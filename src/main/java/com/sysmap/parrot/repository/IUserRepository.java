package com.sysmap.parrot.repository;

import com.sysmap.parrot.model.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface IUserRepository extends MongoRepository<User, UUID> {
    Optional<User> findUserByEmail(String email);
}
