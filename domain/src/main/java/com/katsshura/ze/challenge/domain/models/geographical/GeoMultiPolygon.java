package com.katsshura.ze.challenge.domain.models.geographical;

import lombok.Getter;

import java.util.List;
import java.util.Objects;

@Getter
public class GeoMultiPolygon {
    private final List<GeoPolygon> polygons;
    private final String type;

    public GeoMultiPolygon(List<GeoPolygon> polygons) {
        this.polygons = polygons;
        type = "MultiPolygon";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GeoMultiPolygon)) return false;
        GeoMultiPolygon that = (GeoMultiPolygon) o;
        return getPolygons().equals(that.getPolygons());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPolygons());
    }
}
