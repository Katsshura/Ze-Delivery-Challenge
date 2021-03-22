package com.katsshura.ze.challenge.infrastructure.mongo.services;

import com.katsshura.ze.challenge.domain.interfaces.PartnerDataManagement;
import com.katsshura.ze.challenge.domain.models.Partner;
import com.katsshura.ze.challenge.domain.models.geographical.GeographicalPartner;
import com.katsshura.ze.challenge.infrastructure.mongo.repositories.PartnerRepository;
import com.katsshura.ze.challenge.infrastructure.mongo.util.PartnerConversion;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PartnerMongoDatabase implements PartnerDataManagement {

    private final PartnerRepository repository;
    private final PartnerConversion partnerConversion;

    public PartnerMongoDatabase(PartnerRepository repository, PartnerConversion partnerConversion) {
        this.repository = repository;
        this.partnerConversion = partnerConversion;
    }

    @Override
    public boolean save(Partner partner) {
        var representation = partnerConversion.toRepresentation(partner);
        try {
            repository.save(representation);
            return true;
        }catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Partner find(String id) {
        var result = repository.findById(id).isPresent()
                ? partnerConversion.toPartner(repository.findById(id).get())
                : null;

        return result;
    }

    @Override
    public List<Partner> findAll() {
        var result = repository.findAll();

        if (result == null) return null;

        var mapped = result
                .stream()
                .map(partnerConversion::toPartner)
                .collect(Collectors.toList());
        return mapped;
    }

    @Override
    public Set<GeographicalPartner> findAllGeographicalPartner() {
        var partners = findAll();

        if(partners == null) return null;

        var result = partners
                .stream()
                .map(partnerConversion::toGeographicalPartner)
                .collect(Collectors.toSet());

        return result;
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
