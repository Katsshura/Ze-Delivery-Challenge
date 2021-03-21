package com.katsshura.ze.challenge.infrastructure;

import com.katsshura.ze.challenge.domain.models.GeoInformation;
import com.katsshura.ze.challenge.infrastructure.mocked.MockedDomainPartners;
import com.katsshura.ze.challenge.infrastructure.mocked.MockedGeoInformation;
import com.katsshura.ze.challenge.infrastructure.mocked.MockedGeoJsonMultiPolygons;
import com.katsshura.ze.challenge.infrastructure.mocked.MockedRepresentationPartners;
import com.katsshura.ze.challenge.infrastructure.mongo.util.PartnerConversion;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.mongodb.core.geo.GeoJsonMultiPolygon;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PartnerConversionTest {

    @DisplayName("Should Return a Valid Partner Domain Model Given a Partner Representation Model")
    @Test
    void testPartnerToRepresentationConversion() {
        var domainPartner = MockedDomainPartners.getMockedPartners().get(0);
        var representationPartner = MockedRepresentationPartners.getMockedPartners().get(0);
        var partnerConversion = new PartnerConversion();
        var result = partnerConversion.toRepresentation(domainPartner);

        assertAll(
                () -> assertEquals(representationPartner.getId(), result.getId()),
                () -> assertEquals(representationPartner.getTradingName(), result.getTradingName()),
                () -> assertEquals(representationPartner.getOwnerName(), result.getOwnerName()),
                () -> assertEquals(representationPartner.getDocument(), result.getDocument()),
                () -> assertEquals(representationPartner.getCoverageArea().getType(), result.getCoverageArea().getType()),
                () -> assertEquals(representationPartner.getCoverageArea().getCoordinates().get(0).getCoordinates().get(0).getCoordinates().get(0), result.getCoverageArea().getCoordinates().get(0).getCoordinates().get(0).getCoordinates().get(0)),
                () -> assertEquals(representationPartner.getCoverageArea().getCoordinates().get(0).getCoordinates().get(0).getCoordinates().get(1), result.getCoverageArea().getCoordinates().get(0).getCoordinates().get(0).getCoordinates().get(1)),
                () -> assertEquals(representationPartner.getCoverageArea().getCoordinates().get(0).getCoordinates().get(0).getCoordinates().get(2), result.getCoverageArea().getCoordinates().get(0).getCoordinates().get(0).getCoordinates().get(2)),
                () -> assertEquals(representationPartner.getCoverageArea().getCoordinates().get(0).getCoordinates().get(0).getCoordinates().get(3), result.getCoverageArea().getCoordinates().get(0).getCoordinates().get(0).getCoordinates().get(3)),
                () -> assertEquals(representationPartner.getCoverageArea().getCoordinates().get(1).getCoordinates().get(0).getCoordinates().get(0), result.getCoverageArea().getCoordinates().get(1).getCoordinates().get(0).getCoordinates().get(0)),
                () -> assertEquals(representationPartner.getCoverageArea().getCoordinates().get(1).getCoordinates().get(0).getCoordinates().get(1), result.getCoverageArea().getCoordinates().get(1).getCoordinates().get(0).getCoordinates().get(1)),
                () -> assertEquals(representationPartner.getCoverageArea().getCoordinates().get(1).getCoordinates().get(0).getCoordinates().get(2), result.getCoverageArea().getCoordinates().get(1).getCoordinates().get(0).getCoordinates().get(2)),
                () -> assertEquals(representationPartner.getCoverageArea().getCoordinates().get(1).getCoordinates().get(0).getCoordinates().get(3), result.getCoverageArea().getCoordinates().get(1).getCoordinates().get(0).getCoordinates().get(3)),
                () -> assertEquals(representationPartner.getCoverageArea().getCoordinates().get(1).getCoordinates().get(0).getCoordinates().get(4), result.getCoverageArea().getCoordinates().get(1).getCoordinates().get(0).getCoordinates().get(4)),
                () -> assertEquals(representationPartner.getAddress().getType(), result.getAddress().getType()),
                () -> assertEquals(representationPartner.getAddress().getX(), result.getAddress().getX()),
                () -> assertEquals(representationPartner.getAddress().getY(), result.getAddress().getY())
        );
    }


    @DisplayName("Should Return a Valid Partner Representation Model Given a Partner Domain Model")
    @Test
    void testRepresentationToPartnerConversion() {
        var domainPartner = MockedDomainPartners.getMockedPartners().get(0);
        var representationPartner = MockedRepresentationPartners.getMockedPartners().get(0);
        var partnerConversion = new PartnerConversion();
        var result = partnerConversion.toPartner(representationPartner);

        assertAll(
                () -> assertEquals(domainPartner.getId(), result.getId()),
                () -> assertEquals(domainPartner.getTradingName(), result.getTradingName()),
                () -> assertEquals(domainPartner.getOwnerName(), result.getOwnerName()),
                () -> assertEquals(domainPartner.getDocument(), result.getDocument()),
                () -> assertEquals(domainPartner.getCoverageArea().getType(), result.getCoverageArea().getType()),
                () -> assertEquals(domainPartner.getCoverageArea().getCoordinates().get(0).get(0).get(0)[1], result.getCoverageArea().getCoordinates().get(0).get(0).get(0)[1]),
                () -> assertEquals(domainPartner.getCoverageArea().getCoordinates().get(0).get(0).get(1)[0], result.getCoverageArea().getCoordinates().get(0).get(0).get(1)[0]),
                () -> assertEquals(domainPartner.getCoverageArea().getCoordinates().get(0).get(0).get(0)[0], result.getCoverageArea().getCoordinates().get(0).get(0).get(0)[0]),
                () -> assertEquals(domainPartner.getCoverageArea().getCoordinates().get(0).get(0).get(1)[1], result.getCoverageArea().getCoordinates().get(0).get(0).get(1)[1]),
                () -> assertEquals(domainPartner.getCoverageArea().getCoordinates().get(0).get(0).get(2)[0], result.getCoverageArea().getCoordinates().get(0).get(0).get(2)[0]),
                () -> assertEquals(domainPartner.getCoverageArea().getCoordinates().get(0).get(0).get(2)[1], result.getCoverageArea().getCoordinates().get(0).get(0).get(2)[1]),
                () -> assertEquals(domainPartner.getCoverageArea().getCoordinates().get(0).get(0).get(3)[0], result.getCoverageArea().getCoordinates().get(0).get(0).get(3)[0]),
                () -> assertEquals(domainPartner.getCoverageArea().getCoordinates().get(0).get(0).get(3)[1], result.getCoverageArea().getCoordinates().get(0).get(0).get(3)[1]),
                () -> assertEquals(domainPartner.getCoverageArea().getCoordinates().get(0).get(1).get(0)[0], result.getCoverageArea().getCoordinates().get(0).get(1).get(0)[0]),
                () -> assertEquals(domainPartner.getCoverageArea().getCoordinates().get(0).get(1).get(0)[1], result.getCoverageArea().getCoordinates().get(0).get(1).get(0)[1]),
                () -> assertEquals(domainPartner.getCoverageArea().getCoordinates().get(0).get(1).get(1)[0], result.getCoverageArea().getCoordinates().get(0).get(1).get(1)[0]),
                () -> assertEquals(domainPartner.getCoverageArea().getCoordinates().get(0).get(1).get(1)[1], result.getCoverageArea().getCoordinates().get(0).get(1).get(1)[1]),
                () -> assertEquals(domainPartner.getCoverageArea().getCoordinates().get(0).get(1).get(2)[0], result.getCoverageArea().getCoordinates().get(0).get(1).get(2)[0]),
                () -> assertEquals(domainPartner.getCoverageArea().getCoordinates().get(0).get(1).get(2)[1], result.getCoverageArea().getCoordinates().get(0).get(1).get(2)[1]),
                () -> assertEquals(domainPartner.getCoverageArea().getCoordinates().get(0).get(1).get(3)[0], result.getCoverageArea().getCoordinates().get(0).get(1).get(3)[0]),
                () -> assertEquals(domainPartner.getCoverageArea().getCoordinates().get(0).get(1).get(3)[1], result.getCoverageArea().getCoordinates().get(0).get(1).get(3)[1]),
                () -> assertEquals(domainPartner.getCoverageArea().getCoordinates().get(0).get(1).get(4)[0], result.getCoverageArea().getCoordinates().get(0).get(1).get(4)[0]),
                () -> assertEquals(domainPartner.getCoverageArea().getCoordinates().get(0).get(1).get(4)[1], result.getCoverageArea().getCoordinates().get(0).get(1).get(4)[1]),
                () -> assertEquals(domainPartner.getAddress().getType(), result.getAddress().getType()),
                () -> assertEquals(domainPartner.getAddress().getCoordinates()[0], result.getAddress().getCoordinates()[0]),
                () -> assertEquals(domainPartner.getAddress().getCoordinates()[1], result.getAddress().getCoordinates()[1])
        );
    }

    @DisplayName("Should Return a Valid GeoJsonMultiPolygon Given a GeoInformation")
    @Test
    void testGeoInfoToMultiPolygonConversion() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        var geoInfo = MockedGeoInformation.getMockedGeoInformation().get(0);
        var multiPolygon = MockedGeoJsonMultiPolygons.getMockedMultiPolygons().get(0);
        var partnerConversion = new PartnerConversion();
        var privateMethod = partnerConversion.getClass().getDeclaredMethod("toMultiPolygon", GeoInformation.class);
        privateMethod.setAccessible(true);
        var result = (GeoJsonMultiPolygon)privateMethod.invoke(partnerConversion, geoInfo);

        assertAll(
                () -> assertEquals(multiPolygon.getType(), result.getType()),
                () -> assertEquals(multiPolygon.getCoordinates().get(0).getCoordinates().get(0).getCoordinates().get(0), result.getCoordinates().get(0).getCoordinates().get(0).getCoordinates().get(0)),
                () -> assertEquals(multiPolygon.getCoordinates().get(0).getCoordinates().get(0).getCoordinates().get(1), result.getCoordinates().get(0).getCoordinates().get(0).getCoordinates().get(1)),
                () -> assertEquals(multiPolygon.getCoordinates().get(0).getCoordinates().get(0).getCoordinates().get(2), result.getCoordinates().get(0).getCoordinates().get(0).getCoordinates().get(2)),
                () -> assertEquals(multiPolygon.getCoordinates().get(0).getCoordinates().get(0).getCoordinates().get(3), result.getCoordinates().get(0).getCoordinates().get(0).getCoordinates().get(3)),
                () -> assertEquals(multiPolygon.getCoordinates().get(1).getCoordinates().get(0).getCoordinates().get(0), result.getCoordinates().get(1).getCoordinates().get(0).getCoordinates().get(0)),
                () -> assertEquals(multiPolygon.getCoordinates().get(1).getCoordinates().get(0).getCoordinates().get(1), result.getCoordinates().get(1).getCoordinates().get(0).getCoordinates().get(1)),
                () -> assertEquals(multiPolygon.getCoordinates().get(1).getCoordinates().get(0).getCoordinates().get(2), result.getCoordinates().get(1).getCoordinates().get(0).getCoordinates().get(2)),
                () -> assertEquals(multiPolygon.getCoordinates().get(1).getCoordinates().get(0).getCoordinates().get(3), result.getCoordinates().get(1).getCoordinates().get(0).getCoordinates().get(3)),
                () -> assertEquals(multiPolygon.getCoordinates().get(1).getCoordinates().get(0).getCoordinates().get(4), result.getCoordinates().get(1).getCoordinates().get(0).getCoordinates().get(4))
        );
    }

    @DisplayName("Should Return a Valid GeoInformation Given a GeoJsonMultiPolygon")
    @Test
    void testMultiPolygonToGeoInfoConversion() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        var geoInfo = MockedGeoInformation.getMockedGeoInformation().get(0);
        var multiPolygon = MockedGeoJsonMultiPolygons.getMockedMultiPolygons().get(0);
        var partnerConversion = new PartnerConversion();
        var privateMethod = partnerConversion.getClass().getDeclaredMethod("toGeoInfo", GeoJsonMultiPolygon.class);
        privateMethod.setAccessible(true);
        var result = (GeoInformation<List<List<List<double[]>>>>)privateMethod.invoke(partnerConversion, multiPolygon);

        assertAll(
                () -> assertEquals(geoInfo.getType(), result.getType()),
                () -> assertEquals(geoInfo.getCoordinates().get(0).get(0).get(0)[0], result.getCoordinates().get(0).get(0).get(0)[0]),
                () -> assertEquals(geoInfo.getCoordinates().get(0).get(0).get(0)[1], result.getCoordinates().get(0).get(0).get(0)[1]),
                () -> assertEquals(geoInfo.getCoordinates().get(0).get(0).get(1)[0], result.getCoordinates().get(0).get(0).get(1)[0]),
                () -> assertEquals(geoInfo.getCoordinates().get(0).get(0).get(1)[1], result.getCoordinates().get(0).get(0).get(1)[1]),
                () -> assertEquals(geoInfo.getCoordinates().get(0).get(0).get(2)[0], result.getCoordinates().get(0).get(0).get(2)[0]),
                () -> assertEquals(geoInfo.getCoordinates().get(0).get(0).get(2)[1], result.getCoordinates().get(0).get(0).get(2)[1]),
                () -> assertEquals(geoInfo.getCoordinates().get(0).get(0).get(3)[0], result.getCoordinates().get(0).get(0).get(3)[0]),
                () -> assertEquals(geoInfo.getCoordinates().get(0).get(0).get(3)[1], result.getCoordinates().get(0).get(0).get(3)[1]),
                () -> assertEquals(geoInfo.getCoordinates().get(0).get(1).get(0)[0], result.getCoordinates().get(0).get(1).get(0)[0]),
                () -> assertEquals(geoInfo.getCoordinates().get(0).get(1).get(0)[1], result.getCoordinates().get(0).get(1).get(0)[1]),
                () -> assertEquals(geoInfo.getCoordinates().get(0).get(1).get(1)[0], result.getCoordinates().get(0).get(1).get(1)[0]),
                () -> assertEquals(geoInfo.getCoordinates().get(0).get(1).get(1)[1], result.getCoordinates().get(0).get(1).get(1)[1]),
                () -> assertEquals(geoInfo.getCoordinates().get(0).get(1).get(2)[0], result.getCoordinates().get(0).get(1).get(2)[0]),
                () -> assertEquals(geoInfo.getCoordinates().get(0).get(1).get(2)[1], result.getCoordinates().get(0).get(1).get(2)[1]),
                () -> assertEquals(geoInfo.getCoordinates().get(0).get(1).get(3)[0], result.getCoordinates().get(0).get(1).get(3)[0]),
                () -> assertEquals(geoInfo.getCoordinates().get(0).get(1).get(3)[1], result.getCoordinates().get(0).get(1).get(3)[1]),
                () -> assertEquals(geoInfo.getCoordinates().get(0).get(1).get(4)[0], result.getCoordinates().get(0).get(1).get(4)[0]),
                () -> assertEquals(geoInfo.getCoordinates().get(0).get(1).get(4)[1], result.getCoordinates().get(0).get(1).get(4)[1])
        );
    }
}
