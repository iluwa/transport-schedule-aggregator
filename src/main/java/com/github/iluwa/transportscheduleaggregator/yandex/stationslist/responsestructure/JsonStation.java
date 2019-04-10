package com.github.iluwa.transportscheduleaggregator.yandex.stationslist.responsestructure;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * This class is part Stations of response
 * Used to deserialize String response to pojo objects
 */
@NoArgsConstructor
@Getter
public class JsonStation {
    @SerializedName("title")
    private String title;

    @SerializedName("codes")
    private JsonCode code;

    @SerializedName("station_type")
    private String stationType;

    @SerializedName("transport_type")
    private String transportType;

    @SerializedName("direction")
    private String direction;

//    @SerializedName("longitude")
//    private double longitude;
//
//    @SerializedName("latitude")
//    private double latitude;
}
