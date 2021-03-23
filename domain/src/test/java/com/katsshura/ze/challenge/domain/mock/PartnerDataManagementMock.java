package com.katsshura.ze.challenge.domain.mock;

import com.katsshura.ze.challenge.domain.interfaces.PartnerDataManagement;
import com.katsshura.ze.challenge.domain.models.geographical.*;
import com.katsshura.ze.challenge.domain.models.Partner;

import java.util.*;

public class PartnerDataManagementMock implements PartnerDataManagement {
    @Override
    public boolean save(Partner partner) {
        return false;
    }

    @Override
    public Partner find(String id) {
        return partners().stream().filter(partner -> partner.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<Partner> findAll() {
        return partners();
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public Set<GeographicalPartner> findAllGeographicalPartner() {
        var partners = Arrays.asList(
                new GeographicalPartner(
                        "1",
                        new Coordinate(20, 40),
                        new GeoMultiPolygon(
                                Collections.singletonList(
                                        new GeoPolygon(
                                                getAllCoordinatesFromPolygon(
                                                    Arrays.asList(
                                                            new Coordinate(-50, -20),
                                                            new Coordinate(-50, 30),
                                                            new Coordinate(-30, 50),
                                                            new Coordinate(40, 50),
                                                            new Coordinate(40, 20),
                                                            new Coordinate(20, -20)
                                                    )
                                                )
                                        )
                                )
                        )
                ),
                new GeographicalPartner(
                        "2",
                        new Coordinate(40, -20),
                        new GeoMultiPolygon(
                                Collections.singletonList(
                                        new GeoPolygon(
                                                getAllCoordinatesFromPolygon(
                                                    Arrays.asList(
                                                            new Coordinate(-20, 20),
                                                            new Coordinate(-20, -60),
                                                            new Coordinate(60, -60),
                                                            new Coordinate(60, 20)
                                                    )
                                                )
                                        )
                                )
                        )
                )
        );
        return new HashSet<>(partners);
    }

    private List<Partner> partners() {
        return Arrays.asList(
                new Partner(
                        "1",
                        "P1",
                        "Teste 1",
                        "1432132123891/0001",
                        new GeoInformation<>(
                                new GeoMultiPolygon(
                                        Collections.singletonList(
                                                new GeoPolygon(
                                                        Arrays.asList(
                                                                new Coordinate(-50, -20),
                                                                new Coordinate(-50, 30),
                                                                new Coordinate(-30, 50),
                                                                new Coordinate(40, 50),
                                                                new Coordinate(40, 20),
                                                                new Coordinate(20, -20)
                                                        )
                                                )
                                        )
                                )
                        ),
                        new GeoInformation<>(new Coordinate(20, 40))
                ),
                new Partner(
                        "2",
                        "P2",
                        "Teste 2",
                        "1432132123891/0002",
                        new GeoInformation<>(
                                new GeoMultiPolygon(
                                        Collections.singletonList(
                                                new GeoPolygon(
                                                        Arrays.asList(
                                                                new Coordinate(-20, 20),
                                                                new Coordinate(-20, -60),
                                                                new Coordinate(60, -60),
                                                                new Coordinate(60, 20)
                                                        )
                                                )
                                        )
                                )
                        ),
                        new GeoInformation<>(new Coordinate(40, -20))
                )
        );
    }

    private List<Coordinate> getAllCoordinatesFromPolygon(List<Coordinate> verticesCoordinates) {
        var allCoordinates = new ArrayList<Coordinate>();

        for (int i = 0; i < verticesCoordinates.size(); i++) {
            var vertice = verticesCoordinates.get(i);
            var nextVertice = i == verticesCoordinates.size()-1 ? verticesCoordinates.get(0) : verticesCoordinates.get(i + 1);

            var x = vertice.getLongitude();
            var y = vertice.getLatitude();

            while (x != nextVertice.getLongitude() || y != nextVertice.getLatitude()) {
                if(x < nextVertice.getLongitude()) {
                    x += 1;
                }else if (x > nextVertice.getLongitude()){
                    x -= 1;
                }

                if(y < nextVertice.getLatitude()) {
                    y += 1;
                }else if(y > nextVertice.getLatitude()) {
                    y -= 1;
                }

                var coordinate = new Coordinate(x, y);

                allCoordinates.add(coordinate);
            }
        }

        return allCoordinates;
    }
}
