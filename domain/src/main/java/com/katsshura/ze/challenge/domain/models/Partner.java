package com.katsshura.ze.challenge.domain.models;

import com.katsshura.ze.challenge.domain.models.geographical.Coordinate;
import com.katsshura.ze.challenge.domain.models.geographical.GeoInformation;
import com.katsshura.ze.challenge.domain.models.geographical.GeoMultiPolygon;
import lombok.Getter;

@Getter
public class Partner {
    private final String id;
    private final String tradingName;
    private final String ownerName;
    private final String document;
    private final GeoInformation<GeoMultiPolygon> coverageArea;
    private final GeoInformation<Coordinate> address;

    public Partner(String id,
                   String tradingName,
                   String ownerName,
                   String document,
                   GeoInformation<GeoMultiPolygon> coverageArea,
                   GeoInformation<Coordinate> address)
    {
        this.id = id;
        this.tradingName = tradingName;
        this.ownerName = ownerName;
        this.document = document;
        this.coverageArea = coverageArea;
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Partner)) return false;
        Partner partner = (Partner) o;
        return getId().equals(partner.getId());
    }
}
