package com.katsshura.ze.challenge.domain.models.geographical;

import lombok.Getter;

import java.util.List;
import java.util.Objects;

@Getter
public class GeoPolygon {
    private final List<Coordinate> coordinates;
    private final String type;

    public GeoPolygon(List<Coordinate> coordinates) {
        this.coordinates = coordinates;
        type = "Polygon";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GeoPolygon)) return false;
        GeoPolygon that = (GeoPolygon) o;
        return getCoordinates().equals(that.getCoordinates());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCoordinates());
    }
}
