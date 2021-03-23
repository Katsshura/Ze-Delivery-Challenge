package com.katsshura.zechallenge.api.viewModels;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeoInformationModel<T> {
    @JsonProperty("type")
    private String type;
    @JsonProperty("coordinates")
    private T coordinates;
}
