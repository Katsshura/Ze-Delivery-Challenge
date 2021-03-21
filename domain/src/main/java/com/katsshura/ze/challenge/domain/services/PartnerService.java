package com.katsshura.ze.challenge.domain.services;

import com.katsshura.ze.challenge.domain.interfaces.PartnerDataManagement;
import com.katsshura.ze.challenge.domain.interfaces.PartnerServiceDefinition;
import com.katsshura.ze.challenge.domain.models.Partner;
import org.springframework.stereotype.Component;

@Component
public class PartnerService implements PartnerServiceDefinition {

    private final PartnerDataManagement partnerDataManagement;

    public PartnerService(PartnerDataManagement partnerDataManagement) {
        this.partnerDataManagement = partnerDataManagement;
    }

    @Override
    public Partner getNearestPartnerBasedOnLocation(double longitude, double latitude) {
        return null;
    }
}
