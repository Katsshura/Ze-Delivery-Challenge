package com.katsshura.ze.challenge.domain.mock;

import com.katsshura.ze.challenge.domain.interfaces.PartnerDataManagement;
import com.katsshura.ze.challenge.domain.models.geographical.GeoInformation;
import com.katsshura.ze.challenge.domain.models.Partner;

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
                        new GeoInformation<>("Point", new double[] { -46.57421, -21.785741 })
                ),
                new Partner(
                        "1",
                        "Adega da Cerveja - Pinheiros",
                        "Zé da Silva",
                        "1432132123891/0001",
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
                        new GeoInformation<>("Point", new double[] { -46.57421, -21.785741 })
                )
        );
    }
}
