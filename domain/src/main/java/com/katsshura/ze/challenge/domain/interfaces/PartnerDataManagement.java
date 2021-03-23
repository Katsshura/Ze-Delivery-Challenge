package com.katsshura.ze.challenge.domain.interfaces;

import com.katsshura.ze.challenge.domain.models.Partner;
import com.katsshura.ze.challenge.domain.models.geographical.GeographicalPartner;

import java.util.List;
import java.util.Set;

public interface PartnerDataManagement {
    boolean save(Partner partner);
    Partner find(String id);
    List<Partner> findAll();
    Set<GeographicalPartner> findAllGeographicalPartner();
    boolean delete(String id);
}
