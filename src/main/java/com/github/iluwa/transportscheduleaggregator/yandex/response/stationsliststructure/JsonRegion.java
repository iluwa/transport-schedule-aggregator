package com.github.iluwa.transportscheduleaggregator.yandex.response.stationsliststructure;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * This class is part Regions of response
 * Used to deserialize String response to pojo objects
 */
@NoArgsConstructor
@Getter
public class JsonRegion {
    @SerializedName("title")
    private String title;

    @SerializedName("codes")
    private JsonCode code;

    @SerializedName("settlements")
    private List<JsonSettlement> settlements;
}
