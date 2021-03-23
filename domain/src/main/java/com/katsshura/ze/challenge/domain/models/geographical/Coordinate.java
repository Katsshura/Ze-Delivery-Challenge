package com.katsshura.ze.challenge.domain.models.geographical;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Coordinate {
    private final String type;
    private final double longitude;
    private final double latitude;

    public Coordinate(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
        type = "Point";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinate)) return false;
        Coordinate that = (Coordinate) o;
        return Double.compare(that.getLongitude(), getLongitude()) == 0 &&
                Double.compare(that.getLatitude(), getLatitude()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLongitude(), getLatitude());
    }
}
