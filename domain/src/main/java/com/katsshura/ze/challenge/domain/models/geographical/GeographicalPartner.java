package com.katsshura.ze.challenge.domain.models.geographical;

import lombok.Getter;

@Getter
public class GeographicalPartner {
    private final String id;
    private final Coordinate address;
    private final GeoMultiPolygon coverageArea;

    public GeographicalPartner(String id, Coordinate address, GeoMultiPolygon coverageArea) {
        this.id = id;
        this.address = address;
        this.coverageArea = coverageArea;
    }
}
