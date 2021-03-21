package com.katsshura.ze.challenge.infrastructure.mocked;

import com.katsshura.ze.challenge.domain.models.geographical.Coordinate;
import com.katsshura.ze.challenge.domain.models.geographical.GeoInformation;
import com.katsshura.ze.challenge.domain.models.geographical.GeoMultiPolygon;
import com.katsshura.ze.challenge.domain.models.geographical.GeoPolygon;

import java.util.Arrays;
import java.util.List;

public class MockedGeoInformation {
    public static List<GeoInformation<GeoMultiPolygon>> getMockedGeoInformation() {
        return Arrays.asList(
                new GeoInformation<>(
                        new GeoMultiPolygon(
                                Arrays.asList(
                                        new GeoPolygon(
                                                Arrays.asList(
                                                        new Coordinate(30, 20),
                                                        new Coordinate(45, 40),
                                                        new Coordinate(10, 40),
                                                        new Coordinate(30, 20)
                                                )
                                        ),
                                        new GeoPolygon(
                                                Arrays.asList(
                                                        new Coordinate(15, 5),
                                                        new Coordinate(40, 10),
                                                        new Coordinate(10, 20),
                                                        new Coordinate(5, 10),
                                                        new Coordinate(15, 5)
                                                )
                                        )
                                )
                        )
                ),
                new GeoInformation<>(
                        new GeoMultiPolygon(
                                Arrays.asList(
                                        new GeoPolygon(
                                                Arrays.asList(
                                                        new Coordinate(1, 1),
                                                        new Coordinate(2, 2),
                                                        new Coordinate(3, 3),
                                                        new Coordinate(4, 4)
                                                )
                                        ),
                                        new GeoPolygon(
                                                Arrays.asList(
                                                        new Coordinate(5, 5),
                                                        new Coordinate(6, 6),
                                                        new Coordinate(7, 7),
                                                        new Coordinate(8, 8),
                                                        new Coordinate(9, 9)
                                                )
                                        )
                                )
                        )
                )
        );
    }
}
