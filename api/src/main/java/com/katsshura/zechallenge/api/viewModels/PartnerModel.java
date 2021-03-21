package com.katsshura.zechallenge.api.viewModels;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.katsshura.ze.challenge.domain.models.GeoInformation;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PartnerModel {
    @JsonProperty("id")
    private String id;
    @JsonProperty("tradingName")
    private String tradingName;
    @JsonProperty("ownerName")
    private String ownerName;
    @JsonProperty("document")
    private String document;
    @JsonProperty("coverageArea")
    private GeoInformationModel<List<List<List<double[]>>>> coverageArea;
    @JsonProperty("address")
    private GeoInformationModel<double[]> address;
}
