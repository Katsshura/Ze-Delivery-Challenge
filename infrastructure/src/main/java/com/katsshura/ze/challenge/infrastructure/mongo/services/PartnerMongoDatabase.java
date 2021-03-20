package com.katsshura.ze.challenge.infrastructure.mongo.services;

import com.katsshura.ze.challenge.domain.interfaces.PartnerDataManagement;
import com.katsshura.ze.challenge.domain.models.Partner;
import com.katsshura.ze.challenge.infrastructure.mongo.repositories.PartnerRepository;
import com.katsshura.ze.challenge.infrastructure.mongo.util.PartnerConversion;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PartnerMongoDatabase implements PartnerDataManagement {

    private final PartnerRepository repository;

    public PartnerMongoDatabase(PartnerRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean save(Partner partner) {
        var representation = PartnerConversion.toRepresentation(partner);
        try {
            repository.save(representation);
            return true;
        }catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Partner find(String id) {
        var result = repository.findById(id).get();
        var partner = PartnerConversion.toPartner(result);
        return partner;
    }

    @Override
    public List<Partner> findAll() {
        var result = repository.findAll();
        var mapped = result
                .stream()
                .map(representation -> PartnerConversion.toPartner(representation))
                .collect(Collectors.toList());
        return mapped;
    }

    @Override
    public boolean delete(String id) {
        try {
            repository.deleteById(id);
            return true;
        }catch (Exception e) {
            throw e;
        }
    }
}
