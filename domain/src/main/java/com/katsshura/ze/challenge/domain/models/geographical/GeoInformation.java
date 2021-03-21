package com.katsshura.ze.challenge.domain.models.geographical;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeoInformation <T> {
    private final String type;
    private final T coordinates;

    public GeoInformation(String type, T coordinates) {
        this.type = type;
        this.coordinates = coordinates;
    }
}
