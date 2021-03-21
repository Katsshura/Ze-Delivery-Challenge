package com.katsshura.ze.challenge.infrastructure.mongo.util;

import com.katsshura.ze.challenge.domain.models.geographical.GeoInformation;
import com.katsshura.ze.challenge.domain.models.Partner;
import com.katsshura.ze.challenge.infrastructure.mongo.dtos.PartnerRepresentation;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonMultiPolygon;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PartnerConversion {
    public PartnerRepresentation toRepresentation(Partner partner) {
        var coverageArea = toMultiPolygon(partner.getCoverageArea());
        var longitude = partner.getAddress().getCoordinates()[0];
        var latitude = partner.getAddress().getCoordinates()[1];
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
        var address = new GeoInformation<>(representation.getAddress().getType(), new double[]{longitude, latitude});
        return new Partner(
                representation.getId(),
                representation.getTradingName(),
                representation.getOwnerName(),
                representation.getDocument(),
                coverageArea,
                address
        );
    }

    private GeoJsonMultiPolygon toMultiPolygon(GeoInformation<List<List<List<double[]>>>> coverageArea) {
        var polygons = new ArrayList<GeoJsonPolygon>();

        for (var polygon: coverageArea.getCoordinates()) {
            for (var rings: polygon) {
                var points = new ArrayList<Point>();

                for (var point: rings) {
                    points.add(new Point(point[0], point[1]));
                }
                polygons.add(new GeoJsonPolygon(points));
            }
        }

        return new GeoJsonMultiPolygon(polygons);
    }

    private GeoInformation<List<List<List<double[]>>>> toGeoInfo(GeoJsonMultiPolygon geoJsonMultiPolygon) {
        var polygons = geoJsonMultiPolygon.getCoordinates();

        var polys = new ArrayList<List<double[]>>();

        for (var ring: polygons) {
            var points = new ArrayList<double[]>();
            for (var point: ring) {
                points.add(new double[] { point.getX(), point.getY() });
            }
            polys.add(points);
        }

        var coordinates = new ArrayList<List<List<double[]>>>();
        coordinates.add(polys);

        var geoInfo = new GeoInformation<List<List<List<double[]>>>>(geoJsonMultiPolygon.getType(), coordinates);

        return geoInfo;
    }
}
