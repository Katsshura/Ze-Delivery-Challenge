package com.katsshura.ze.challenge.infrastructure;

import com.katsshura.ze.challenge.domain.models.geographical.GeoInformation;
import com.katsshura.ze.challenge.domain.models.geographical.GeoMultiPolygon;
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
                () -> assertEquals(domainPartner.getCoverageArea().getCoordinates().getType(), result.getCoverageArea().getCoordinates().getType()),
                () -> assertEquals(domainPartner.getCoverageArea().getCoordinates().getPolygons().get(0).getCoordinates().get(0).getLatitude(),  result.getCoverageArea().getCoordinates().getPolygons().get(0).getCoordinates().get(0).getLatitude()),
                () -> assertEquals(domainPartner.getCoverageArea().getCoordinates().getPolygons().get(0).getCoordinates().get(0).getLongitude(), result.getCoverageArea().getCoordinates().getPolygons().get(0).getCoordinates().get(0).getLongitude()),
                () -> assertEquals(domainPartner.getCoverageArea().getCoordinates().getPolygons().get(0).getCoordinates().get(1).getLatitude(),  result.getCoverageArea().getCoordinates().getPolygons().get(0).getCoordinates().get(1).getLatitude()),
                () -> assertEquals(domainPartner.getCoverageArea().getCoordinates().getPolygons().get(0).getCoordinates().get(1).getLongitude(), result.getCoverageArea().getCoordinates().getPolygons().get(0).getCoordinates().get(1).getLongitude()),
                () -> assertEquals(domainPartner.getCoverageArea().getCoordinates().getPolygons().get(0).getCoordinates().get(2).getLatitude(),  result.getCoverageArea().getCoordinates().getPolygons().get(0).getCoordinates().get(2).getLatitude()),
                () -> assertEquals(domainPartner.getCoverageArea().getCoordinates().getPolygons().get(0).getCoordinates().get(2).getLongitude(), result.getCoverageArea().getCoordinates().getPolygons().get(0).getCoordinates().get(2).getLongitude()),
                () -> assertEquals(domainPartner.getCoverageArea().getCoordinates().getPolygons().get(0).getCoordinates().get(3).getLatitude(),  result.getCoverageArea().getCoordinates().getPolygons().get(0).getCoordinates().get(3).getLatitude()),
                () -> assertEquals(domainPartner.getCoverageArea().getCoordinates().getPolygons().get(0).getCoordinates().get(3).getLongitude(), result.getCoverageArea().getCoordinates().getPolygons().get(0).getCoordinates().get(3).getLongitude()),
                () -> assertEquals(domainPartner.getCoverageArea().getCoordinates().getPolygons().get(1).getCoordinates().get(0).getLatitude(),  result.getCoverageArea().getCoordinates().getPolygons().get(1).getCoordinates().get(0).getLatitude()),
                () -> assertEquals(domainPartner.getCoverageArea().getCoordinates().getPolygons().get(1).getCoordinates().get(0).getLongitude(), result.getCoverageArea().getCoordinates().getPolygons().get(1).getCoordinates().get(0).getLongitude()),
                () -> assertEquals(domainPartner.getCoverageArea().getCoordinates().getPolygons().get(1).getCoordinates().get(1).getLatitude(),  result.getCoverageArea().getCoordinates().getPolygons().get(1).getCoordinates().get(1).getLatitude()),
                () -> assertEquals(domainPartner.getCoverageArea().getCoordinates().getPolygons().get(1).getCoordinates().get(1).getLongitude(), result.getCoverageArea().getCoordinates().getPolygons().get(1).getCoordinates().get(1).getLongitude()),
                () -> assertEquals(domainPartner.getCoverageArea().getCoordinates().getPolygons().get(1).getCoordinates().get(2).getLatitude(),  result.getCoverageArea().getCoordinates().getPolygons().get(1).getCoordinates().get(2).getLatitude()),
                () -> assertEquals(domainPartner.getCoverageArea().getCoordinates().getPolygons().get(1).getCoordinates().get(2).getLongitude(), result.getCoverageArea().getCoordinates().getPolygons().get(1).getCoordinates().get(2).getLongitude()),
                () -> assertEquals(domainPartner.getCoverageArea().getCoordinates().getPolygons().get(1).getCoordinates().get(3).getLatitude(),  result.getCoverageArea().getCoordinates().getPolygons().get(1).getCoordinates().get(3).getLatitude()),
                () -> assertEquals(domainPartner.getCoverageArea().getCoordinates().getPolygons().get(1).getCoordinates().get(3).getLongitude(), result.getCoverageArea().getCoordinates().getPolygons().get(1).getCoordinates().get(3).getLongitude()),
                () -> assertEquals(domainPartner.getCoverageArea().getCoordinates().getPolygons().get(1).getCoordinates().get(4).getLatitude(),  result.getCoverageArea().getCoordinates().getPolygons().get(1).getCoordinates().get(4).getLatitude()),
                () -> assertEquals(domainPartner.getCoverageArea().getCoordinates().getPolygons().get(1).getCoordinates().get(4).getLongitude(), result.getCoverageArea().getCoordinates().getPolygons().get(1).getCoordinates().get(4).getLongitude()),
                () -> assertEquals(domainPartner.getAddress().getCoordinates().getType(), result.getAddress().getCoordinates().getType()),
                () -> assertEquals(domainPartner.getAddress().getCoordinates().getLongitude(), result.getAddress().getCoordinates().getLongitude()),
                () -> assertEquals(domainPartner.getAddress().getCoordinates().getLatitude(), result.getAddress().getCoordinates().getLatitude())
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
                () -> assertEquals(multiPolygon.getCoordinates().get(1).getCoordinates().get(0).getCoordinates().get(1), result.getCoordinates().get(1).getCoordinates().get(0).getCoordinates().get(1)),
                () -> assertEquals(multiPolygon.getCoordinates().get(1).getCoordinates().get(0).getCoordinates().get(0), result.getCoordinates().get(1).getCoordinates().get(0).getCoordinates().get(0)),
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
        var result = (GeoInformation<GeoMultiPolygon>)privateMethod.invoke(partnerConversion, multiPolygon);

        assertAll(
                () -> assertEquals(geoInfo.getCoordinates().getType(), result.getCoordinates().getType()),
                () -> assertEquals(geoInfo.getCoordinates().getPolygons().get(0).getCoordinates().get(0).getLatitude(),  result.getCoordinates().getPolygons().get(0).getCoordinates().get(0).getLatitude()),
                () -> assertEquals(geoInfo.getCoordinates().getPolygons().get(0).getCoordinates().get(0).getLongitude(), result.getCoordinates().getPolygons().get(0).getCoordinates().get(0).getLongitude()),
                () -> assertEquals(geoInfo.getCoordinates().getPolygons().get(0).getCoordinates().get(1).getLatitude(),  result.getCoordinates().getPolygons().get(0).getCoordinates().get(1).getLatitude()),
                () -> assertEquals(geoInfo.getCoordinates().getPolygons().get(0).getCoordinates().get(1).getLongitude(), result.getCoordinates().getPolygons().get(0).getCoordinates().get(1).getLongitude()),
                () -> assertEquals(geoInfo.getCoordinates().getPolygons().get(0).getCoordinates().get(2).getLatitude(),  result.getCoordinates().getPolygons().get(0).getCoordinates().get(2).getLatitude()),
                () -> assertEquals(geoInfo.getCoordinates().getPolygons().get(0).getCoordinates().get(2).getLongitude(), result.getCoordinates().getPolygons().get(0).getCoordinates().get(2).getLongitude()),
                () -> assertEquals(geoInfo.getCoordinates().getPolygons().get(0).getCoordinates().get(3).getLatitude(),  result.getCoordinates().getPolygons().get(0).getCoordinates().get(3).getLatitude()),
                () -> assertEquals(geoInfo.getCoordinates().getPolygons().get(0).getCoordinates().get(3).getLongitude(), result.getCoordinates().getPolygons().get(0).getCoordinates().get(3).getLongitude()),
                () -> assertEquals(geoInfo.getCoordinates().getPolygons().get(1).getCoordinates().get(0).getLatitude(),  result.getCoordinates().getPolygons().get(1).getCoordinates().get(0).getLatitude()),
                () -> assertEquals(geoInfo.getCoordinates().getPolygons().get(1).getCoordinates().get(0).getLongitude(), result.getCoordinates().getPolygons().get(1).getCoordinates().get(0).getLongitude()),
                () -> assertEquals(geoInfo.getCoordinates().getPolygons().get(1).getCoordinates().get(1).getLatitude(),  result.getCoordinates().getPolygons().get(1).getCoordinates().get(1).getLatitude()),
                () -> assertEquals(geoInfo.getCoordinates().getPolygons().get(1).getCoordinates().get(1).getLongitude(), result.getCoordinates().getPolygons().get(1).getCoordinates().get(1).getLongitude()),
                () -> assertEquals(geoInfo.getCoordinates().getPolygons().get(1).getCoordinates().get(2).getLatitude(),  result.getCoordinates().getPolygons().get(1).getCoordinates().get(2).getLatitude()),
                () -> assertEquals(geoInfo.getCoordinates().getPolygons().get(1).getCoordinates().get(2).getLongitude(), result.getCoordinates().getPolygons().get(1).getCoordinates().get(2).getLongitude()),
                () -> assertEquals(geoInfo.getCoordinates().getPolygons().get(1).getCoordinates().get(3).getLatitude(),  result.getCoordinates().getPolygons().get(1).getCoordinates().get(3).getLatitude()),
                () -> assertEquals(geoInfo.getCoordinates().getPolygons().get(1).getCoordinates().get(3).getLongitude(), result.getCoordinates().getPolygons().get(1).getCoordinates().get(3).getLongitude()),
                () -> assertEquals(geoInfo.getCoordinates().getPolygons().get(1).getCoordinates().get(4).getLatitude(),  result.getCoordinates().getPolygons().get(1).getCoordinates().get(4).getLatitude()),
                () -> assertEquals(geoInfo.getCoordinates().getPolygons().get(1).getCoordinates().get(4).getLongitude(), result.getCoordinates().getPolygons().get(1).getCoordinates().get(4).getLongitude())
        );
    }
}
