package com.katsshura.ze.challenge.domain.mock;

import com.katsshura.ze.challenge.domain.interfaces.PartnerDataManagement;
import com.katsshura.ze.challenge.domain.models.geographical.Coordinate;
import com.katsshura.ze.challenge.domain.models.geographical.GeoInformation;
import com.katsshura.ze.challenge.domain.models.Partner;
import com.katsshura.ze.challenge.domain.models.geographical.GeoMultiPolygon;
import com.katsshura.ze.challenge.domain.models.geographical.GeoPolygon;

import java.util.Arrays;
import java.util.List;

public class PartnerDataManagementMock implements PartnerDataManagement {
    @Override
    public boolean save(Partner partner) {
        return false;
    }

    @Override
    public Partner find(String id) {
        return null;
    }

    @Override
    public List<Partner> findAll() {
        return partners();
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    private List<Partner> partners() {
        return Arrays.asList(
                new Partner(
                        "1",
                        "Adega da Cerveja - Pinheiros",
                        "Zé da Silva",
                        "1432132123891/0001",
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
                        new GeoInformation<>(new Coordinate(-46.57421, -21.785741))
                ),
                new Partner(
                        "1",
                        "Adega da Cerveja - Pinheiros",
                        "Zé da Silva",
                        "1432132123891/0001",
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
                        new GeoInformation<>(new Coordinate(-46.57421, -21.785741))
                )
        );
    }
}
