package com.katsshura.ze.challenge.infrastructure.mocked;

import com.katsshura.ze.challenge.domain.models.geographical.GeoInformation;
import com.katsshura.ze.challenge.domain.models.Partner;

import java.util.Arrays;
import java.util.List;

public class MockedDomainPartners {
    public static List<Partner> getMockedPartners() {
        return Arrays.asList(
                new Partner(
                        "1",
                        "Adega da Cerveja - Pinheiros",
                        "Zé da Silva",
                        "1432132123891/0001",
                        MockedGeoInformation.getMockedGeoInformation().get(0),
                        new GeoInformation<>("Point", new double[] { -46.57421, -21.785741 })
                )
        );
    }
}
