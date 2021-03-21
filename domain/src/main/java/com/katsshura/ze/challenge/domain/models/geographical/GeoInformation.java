package com.katsshura.ze.challenge.domain.models.geographical;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeoInformation <T> {
    private final T coordinates;

    public GeoInformation(T coordinates) {
        this.coordinates = coordinates;
    }
}
