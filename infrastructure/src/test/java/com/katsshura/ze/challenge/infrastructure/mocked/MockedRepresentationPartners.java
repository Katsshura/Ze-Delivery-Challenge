package com.katsshura.ze.challenge.infrastructure.mocked;

import com.katsshura.ze.challenge.infrastructure.mongo.dtos.PartnerRepresentation;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.util.Arrays;
import java.util.List;

public class MockedRepresentationPartners {
    public static List<PartnerRepresentation> getMockedPartners() {
        return Arrays.asList(
                new PartnerRepresentation(
                        "1",
                        "Adega da Cerveja - Pinheiros",
                        "Zé da Silva",
                        "1432132123891/0001",
                        MockedGeoJsonMultiPolygons.getMockedMultiPolygons().get(0),
                        new GeoJsonPoint(-46.57421, -21.785741)
                )
        );
    }
}
