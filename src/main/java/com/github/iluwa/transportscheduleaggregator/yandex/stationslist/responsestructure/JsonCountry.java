package com.github.iluwa.transportscheduleaggregator.yandex.stationslist.responsestructure;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * This class is part Countries of response
 * Used to deserialize String response to pojo objects
 */
@NoArgsConstructor
@Getter
public class JsonCountry implements StationObject {
    @SerializedName("title")
    private String title;

    @SerializedName("codes")
    private JsonCode code;

    @SerializedName("regions")
    private List<JsonRegion> regions;
}
