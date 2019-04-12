package com.github.iluwa.transportscheduleaggregator.yandex.search.responsestructure;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class JsonTo {
    @SerializedName("code")
    private String code;

    @SerializedName("title")
    private String title;

    @SerializedName("station_type")
    private String stationType;

    @SerializedName("station_type_name")
    private String stationTypeName;

    @SerializedName("popular_title")
    private String popularTitle;

    @SerializedName("short_title")
    private String shortTitle;

    @SerializedName("transport_type")
    private String transport_type;

    @SerializedName("type")
    private String type;
}
