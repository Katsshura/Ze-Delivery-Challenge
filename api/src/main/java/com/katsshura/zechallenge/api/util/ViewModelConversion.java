package com.katsshura.zechallenge.api.util;

import com.katsshura.ze.challenge.domain.models.Partner;
import com.katsshura.ze.challenge.domain.models.geographical.Coordinate;
import com.katsshura.ze.challenge.domain.models.geographical.GeoInformation;
import com.katsshura.ze.challenge.domain.models.geographical.GeoMultiPolygon;
import com.katsshura.ze.challenge.domain.models.geographical.GeoPolygon;
import com.katsshura.zechallenge.api.viewModels.CoordinateModel;
import com.katsshura.zechallenge.api.viewModels.GeoInformationModel;
import com.katsshura.zechallenge.api.viewModels.PartnerModel;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ViewModelConversion {

    public Partner toDomainPartner(PartnerModel model) {
        var coverageArea = getMultiPolygonCoordinates(model.getCoverageArea().getCoordinates());
        var longitude = model.getAddress().getCoordinates()[0];
        var latitude = model.getAddress().getCoordinates()[1];
        var address = new GeoInformation<>(new Coordinate(longitude, latitude));
        return new Partner(
                model.getId(),
                model.getTradingName(),
                model.getOwnerName(),
                model.getDocument(),
                coverageArea,
                address
        );
    }

    public PartnerModel toViewModelPartner(Partner partner) {
        var model = new PartnerModel();

        model.setId(partner.getId());
        model.setTradingName(partner.getTradingName());
        model.setDocument(partner.getDocument());
        model.setOwnerName(partner.getOwnerName());

        var address = new GeoInformationModel<double[]>();

        address.setCoordinates(new double[] {
                partner.getAddress().getCoordinates().getLongitude(),
                partner.getAddress().getCoordinates().getLatitude()
        });

        address.setType(partner.getAddress().getCoordinates().getType());

        var coverageArea = new GeoInformationModel<List<List<List<double[]>>>>();

        coverageArea.setType(partner.getCoverageArea().getCoordinates().getType());

        coverageArea.setCoordinates(getListCoordinates(partner.getCoverageArea().getCoordinates()));

        model.setAddress(address);
        model.setCoverageArea(coverageArea);

        return model;
    }

    public Coordinate toDomainCoordinate(CoordinateModel model) {
        return new Coordinate(model.getLongitude(), model.getLatitude());
    }

    private List<List<List<double[]>>> getListCoordinates(GeoMultiPolygon coordinates) {

        var polys = new ArrayList<List<double[]>>();

        for (var polygon: coordinates.getPolygons()) {
            var points = new ArrayList<double[]>();
            for (var point: polygon.getCoordinates()) {
                points.add(new double[] { point.getLongitude(), point.getLatitude() });
            }
            polys.add(points);
        }

        var location = new ArrayList<List<List<double[]>>>();
        location.add(polys);

        return location;
    }

    private GeoInformation<GeoMultiPolygon> getMultiPolygonCoordinates(List<List<List<double[]>>> coverageArea) {
        var polygons = new ArrayList<GeoPolygon>();

        for (var polygon: coverageArea) {
            for (var rings: polygon) {
                var points = new ArrayList<Coordinate>();

                for (var point: rings) {
                    points.add(new Coordinate(point[0], point[1]));
                }
                polygons.add(new GeoPolygon(points));
            }
        }

        var result = new GeoMultiPolygon(polygons);

        return new GeoInformation<>(result);
    }
}
