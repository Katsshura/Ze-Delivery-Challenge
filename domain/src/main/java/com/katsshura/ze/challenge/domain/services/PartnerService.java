package com.katsshura.ze.challenge.domain.services;

import com.katsshura.ze.challenge.domain.interfaces.PartnerDataManagement;
import com.katsshura.ze.challenge.domain.interfaces.PartnerServiceDefinition;
import com.katsshura.ze.challenge.domain.models.Partner;
import com.katsshura.ze.challenge.domain.models.geographical.Coordinate;
import com.katsshura.ze.challenge.domain.models.geographical.GeoMultiPolygon;
import com.katsshura.ze.challenge.domain.models.geographical.GeoPolygon;
import com.katsshura.ze.challenge.domain.models.geographical.GeographicalPartner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Component
public class PartnerService implements PartnerServiceDefinition {

    private final PartnerDataManagement partnerDataManagement;

    public PartnerService(PartnerDataManagement partnerDataManagement) {
        this.partnerDataManagement = partnerDataManagement;
    }

    @Override
    public Partner getNearestPartnerBasedOnLocation(Coordinate actual) {
        var geographicalPartners = partnerDataManagement.findAllGeographicalPartner();
        var sortedNearestLocations = new TreeMap<Double, String>();

        for (var geoPartner : geographicalPartners) {
            var polygons = geoPartner.getCoverageArea();

            for (var polygon : polygons.getPolygons()) {
                var coordinates = this.getAllCoordinatesFromPolygon(polygon);
                var xCoords = getXCoordinates(coordinates);
                var yCoords = getYCoordinates(coordinates);

                if (this.isCoordinateInsidePolygon(coordinates.size(), xCoords, yCoords, actual) || coordinates.contains(actual)) {
                    var distance = calculateDistanceBetweenCoordinates(actual, geoPartner.getAddress());
                    sortedNearestLocations.put(distance, geoPartner.getId());
                }
            }
        }

        if (sortedNearestLocations.isEmpty()) return null;

        var nearestPartnerId = sortedNearestLocations.firstEntry().getValue();

        return partnerDataManagement.find(nearestPartnerId);
    }

    private double[] getXCoordinates(Set<Coordinate> coordinates) {
        var xCoordinates = new double[coordinates.size()];
        var coords = coordinates.iterator();

        for (int i = 0; i < coordinates.size(); i++) {
            xCoordinates[i] = coords.next().getLongitude();
        }

        return xCoordinates;
    }

    private double[] getYCoordinates(Set<Coordinate> coordinates) {
        var yCoordinates = new double[coordinates.size()];
        var coords = coordinates.iterator();

        for (int i = 0; i < coordinates.size(); i++) {
            yCoordinates[i] = coords.next().getLatitude();
        }

        return yCoordinates;
    }

    private double calculateDistanceBetweenCoordinates(Coordinate actual, Coordinate destiny) {
        //Formula to calculate distance: d=√((x1-x2)²+(y1-y2)²)

        var x1 = actual.getLongitude();
        var x2 = destiny.getLongitude();
        var y1 = actual.getLatitude();
        var y2 = destiny.getLatitude();

        var firstModule = x2 - x1;
        var secondModule = y2 - y1;

        firstModule = Math.pow(firstModule, 2);
        secondModule = Math.pow(secondModule, 2);

        var res = firstModule + secondModule;

        res = Math.sqrt(res);

        return res;
    }

    private boolean isCoordinateInsidePolygon(int totalVertices, double[] xCoordinates, double[] yCoordinates, Coordinate target) {
        var j = totalVertices - 1;
        var isContained = false;

        for (var i = 0; i < totalVertices; i++) {
            if ((yCoordinates[i] < target.getLatitude() && yCoordinates[j] >= target.getLatitude()
                    || yCoordinates[j] < target.getLatitude() && yCoordinates[i] >= target.getLatitude())
                    && (xCoordinates[i] <= target.getLongitude() || xCoordinates[j] <= target.getLongitude())) {

                if (xCoordinates[i] + (target.getLatitude() - yCoordinates[i]) /
                        (yCoordinates[j] - yCoordinates[i]) *
                        (xCoordinates[j] - xCoordinates[i]) < target.getLongitude()) {
                    isContained = !isContained;
                }
            }
            j = i;
        }

        return isContained;
    }

    public HashSet<Coordinate> getAllCoordinatesFromPolygon(GeoPolygon polygon) {
        return new HashSet<>(polygon.getCoordinates());
    }
}
