package com.katsshura.ze.challenge.infrastructure.mongo.dtos;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonMultiPolygon;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "partners")
@Getter
public class PartnerRepresentation {
    @Id
    private final String id;
    private final String tradingName;
    private final String ownerName;
    @Indexed(unique = true)
    private final String document;
    @GeoSpatialIndexed
    private final GeoJsonMultiPolygon coverageArea;
    @GeoSpatialIndexed
    private final GeoJsonPoint address;

    public PartnerRepresentation(String id,
                                 String tradingName,
                                 String ownerName,
                                 String document,
                                 GeoJsonMultiPolygon coverageArea,
                                 GeoJsonPoint address)
    {
        this.id = id;
        this.tradingName = tradingName;
        this.ownerName = ownerName;
        this.document = document;
        this.coverageArea = coverageArea;
        this.address = address;
    }
}
