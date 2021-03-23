package com.katsshura.ze.challenge.infrastructure.mongo.util;

import com.katsshura.ze.challenge.domain.models.geographical.*;
import com.katsshura.ze.challenge.domain.models.Partner;
import com.katsshura.ze.challenge.infrastructure.mongo.dtos.PartnerRepresentation;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonMultiPolygon;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class PartnerConversion {
    public PartnerRepresentation toRepresentation(Partner partner) {
        var coverageArea = toMultiPolygon(partner.getCoverageArea());
        var longitude = partner.getAddress().getCoordinates().getLongitude();
        var latitude = partner.getAddress().getCoordinates().getLatitude();
        var address = new GeoJsonPoint(longitude, latitude);
        return new PartnerRepresentation(
                partner.getId(),
                partner.getTradingName(),
                partner.getOwnerName(),
                partner.getDocument(),
                coverageArea,
                address
        );
    }

    public Partner toPartner(PartnerRepresentation representation) {
        var coverageArea = toGeoInfo(representation.getCoverageArea());
        var longitude = representation.getAddress().getX();
        var latitude = representation.getAddress().getY();
        var address = new GeoInformation<>(new Coordinate(longitude, latitude));
        return new Partner(
                representation.getId(),
                representation.getTradingName(),
                representation.getOwnerName(),
                representation.getDocument(),
                coverageArea,
                address
        );
    }

    public GeographicalPartner toGeographicalPartner(Partner partner) {
        var coverageArea = partner.getCoverageArea().getCoordinates();
        return new GeographicalPartner(
                partner.getId(),
                partner.getAddress().getCoordinates(),
                coverageArea
        );
    }

    private GeoJsonMultiPolygon toMultiPolygon(GeoInformation<GeoMultiPolygon> coverageArea) {
        var polygons = new ArrayList<GeoJsonPolygon>();

        for (var polygon: coverageArea.getCoordinates().getPolygons()) {

            var points = new ArrayList<Point>();

            for (var coordinate: polygon.getCoordinates()) {
                points.add(new Point(coordinate.getLongitude(), coordinate.getLatitude()));
            }

            polygons.add(new GeoJsonPolygon(points));
        }

        return new GeoJsonMultiPolygon(polygons);
    }

    private GeoInformation<GeoMultiPolygon> toGeoInfo(GeoJsonMultiPolygon geoJsonMultiPolygon) {
        var polygons = geoJsonMultiPolygon.getCoordinates();

        var polys = new ArrayList<GeoPolygon>();

        for (var ring: polygons) {
            var points = new ArrayList<Coordinate>();
            for (var point: ring) {
                points.add(new Coordinate(point.getX(), point.getY()));
            }
            polys.add(new GeoPolygon(points));
        }

        var coordinates = new GeoMultiPolygon(polys);

        var geoInfo = new GeoInformation<>(coordinates);

        return geoInfo;
    }
}
