package com.github.iluwa.transportscheduleaggregator.yandex.stationslist.responsestructure;

import com.github.iluwa.transportscheduleaggregator.yandex.HeadOfResponse;
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
public class StationsListResponse implements HeadOfResponse {
    @SerializedName("countries")
    private List<JsonCountry> countries;
}
