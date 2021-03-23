package com.katsshura.ze.challenge.domain.interfaces;

import com.katsshura.ze.challenge.domain.models.Partner;
import com.katsshura.ze.challenge.domain.models.geographical.Coordinate;

public interface PartnerServiceDefinition {
    Partner getNearestPartnerBasedOnLocation(Coordinate actual);
}
