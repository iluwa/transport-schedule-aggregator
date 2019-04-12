package com.github.iluwa.transportscheduleaggregator.yandex.search.responsestructure;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class JsonThread {
    @SerializedName("uid")
    private String uid;

    @SerializedName("title")
    private String title;

    @SerializedName("number")
    private String number;

    @SerializedName("short_title")
    private String shortTitle;

    @SerializedName("transport_type")
    private String transportType;

    @SerializedName("vehicle")
    private String vehicle;

    @SerializedName("express_type")
    private String expressType;
}
