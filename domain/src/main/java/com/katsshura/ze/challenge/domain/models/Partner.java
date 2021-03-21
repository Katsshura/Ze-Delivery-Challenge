package com.katsshura.ze.challenge.domain.models;

import com.katsshura.ze.challenge.domain.models.geographical.GeoInformation;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Partner {
    private final String id;
    private final String tradingName;
    private final String ownerName;
    private final String document;
    private final GeoInformation<List<List<List<double[]>>>> coverageArea;
    private final GeoInformation<double[]> address;

    public Partner(String id,
                   String tradingName,
                   String ownerName,
                   String document,
                   GeoInformation<List<List<List<double[]>>>> coverageArea,
                   GeoInformation<double[]> address)
    {
        this.id = id;
        this.tradingName = tradingName;
        this.ownerName = ownerName;
        this.document = document;
        this.coverageArea = coverageArea;
        this.address = address;
    }
}
