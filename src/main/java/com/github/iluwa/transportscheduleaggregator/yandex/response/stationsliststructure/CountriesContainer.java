package com.github.iluwa.transportscheduleaggregator.yandex.response.stationsliststructure;

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
public class CountriesContainer {
    @SerializedName("countries")
    private List<JsonCountry> countries;
}
