package com.katsshura.ze.challenge.domain.interfaces;

import com.katsshura.ze.challenge.domain.models.Partner;

import java.util.List;

public interface PartnerDataManagement {
    boolean save(Partner partner);
    Partner find(String id);
    List<Partner> findAll();
    boolean delete(String id);
}
