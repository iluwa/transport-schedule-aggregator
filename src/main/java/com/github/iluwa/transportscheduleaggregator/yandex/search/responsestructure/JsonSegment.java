package com.github.iluwa.transportscheduleaggregator.yandex.search.responsestructure;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class JsonSegment {
    @SerializedName("arrival")
    private String arrival;

    @SerializedName("from")
    private JsonFrom from;

    @SerializedName("thread")
    private JsonThread thread;

    @SerializedName("departure_platform")
    private String departurePlatform;

    @SerializedName("departure")
    private String departure;

    @SerializedName("stops")
    private String stops;

    @SerializedName("departure_terminal")
    private String departureTerminal;

    @SerializedName("to")
    private JsonTo to;

    @SerializedName("has_transfers")
    private boolean hasTransfers;

    @SerializedName("tickets_info")
    private JsonThicketsInfo thicketsInfo;

    @SerializedName("duration")
    private int duration;

    @SerializedName("arrival_terminal")
    private String arrivalTerminal;

    @SerializedName("start_date")
    private String startDate;

    @SerializedName("arrival_platform")
    private String arrivalPlatform;
}
