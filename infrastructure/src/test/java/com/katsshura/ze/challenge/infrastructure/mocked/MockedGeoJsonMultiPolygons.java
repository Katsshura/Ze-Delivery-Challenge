package com.katsshura.ze.challenge.infrastructure.mocked;

import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonMultiPolygon;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;

import java.util.Arrays;
import java.util.List;

public class MockedGeoJsonMultiPolygons {
    public static List<GeoJsonMultiPolygon> getMockedMultiPolygons() {
        return Arrays.asList(
                new GeoJsonMultiPolygon(
                        Arrays.asList(
                                new GeoJsonPolygon(
                                        Arrays.asList(
                                                new Point(30, 20),
                                                new Point(45, 40),
                                                new Point(10, 40),
                                                new Point(30, 20)
                                        )
                                ),
                                new GeoJsonPolygon(
                                        Arrays.asList(
                                                new Point(15, 5),
                                                new Point(40, 10),
                                                new Point(10, 20),
                                                new Point(5, 10),
                                                new Point(15, 5)
                                        )
                                )
                        )
                ),

                new GeoJsonMultiPolygon(
                        Arrays.asList(
                                new GeoJsonPolygon(
                                        Arrays.asList(
                                                new Point(1, 1),
                                                new Point(2, 2),
                                                new Point(3, 3),
                                                new Point(4, 4)
                                        )
                                ),
                                new GeoJsonPolygon(
                                        Arrays.asList(
                                                new Point(5, 5),
                                                new Point(6, 6),
                                                new Point(7, 7),
                                                new Point(8, 8),
                                                new Point(9, 9)
                                        )
                                )
                        )
                )
        );
    }
}
