package com.github.iluwa.transportscheduleaggregator.yandex.stationslist.responsestructure;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * This class is head (Countries) of response
 * Used to deserialize String response to pojo objects
 */
@NoArgsConstructor
@Getter
public class StationsListResponse {
    @SerializedName("countries")
    private List<JsonCountry> countries;
}
