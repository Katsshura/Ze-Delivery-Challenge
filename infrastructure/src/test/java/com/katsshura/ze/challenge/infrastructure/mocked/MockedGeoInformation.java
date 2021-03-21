package com.katsshura.ze.challenge.infrastructure.mocked;

import com.katsshura.ze.challenge.domain.models.GeoInformation;

import java.util.Arrays;
import java.util.List;

public class MockedGeoInformation {
    public static List<GeoInformation<List<List<List<double[]>>>>> getMockedGeoInformation() {
        return Arrays.asList(
                new GeoInformation<>(
                        "MultiPolygon",
                        Arrays.asList(
                                Arrays.asList(
                                        Arrays.asList(
                                                new double[] { 30, 20 },
                                                new double[] { 45, 40 },
                                                new double[] { 10, 40 },
                                                new double[] { 30, 20 }
                                        ),
                                        Arrays.asList(
                                                new double[] { 15, 5 },
                                                new double[] { 40, 10 },
                                                new double[] { 10, 20 },
                                                new double[] { 5, 10 },
                                                new double[] { 15, 5 }
                                        )
                                )
                        )
                ),

                new GeoInformation<>(
                        "MultiPolygon",
                        Arrays.asList(
                                Arrays.asList(
                                        Arrays.asList(
                                                new double[] { 1, 1 },
                                                new double[] { 2, 2 },
                                                new double[] { 3, 3 },
                                                new double[] { 4, 4 }
                                        ),
                                        Arrays.asList(
                                                new double[] { 5, 5 },
                                                new double[] { 6, 6 },
                                                new double[] { 7, 7 },
                                                new double[] { 8, 8 },
                                                new double[] { 9, 9 }
                                        )
                                )
                        )
                )
        );
    }
}
