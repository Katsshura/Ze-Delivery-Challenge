package com.katsshura.ze.challenge.domain.interfaces;

import com.katsshura.ze.challenge.domain.models.Partner;

public interface PartnerServiceDefinition {
    Partner getNearestPartnerBasedOnLocation(double longitude, double latitude);
}
