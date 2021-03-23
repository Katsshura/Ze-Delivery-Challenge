package com.katsshura.ze.challenge.infrastructure.mongo.repositories;

import com.katsshura.ze.challenge.infrastructure.mongo.dtos.PartnerRepresentation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerRepository extends MongoRepository<PartnerRepresentation, String> {
}
