package com.github.iluwa.transportscheduleaggregator.yandex.search.responsestructure;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class JsonSearchParams {
    @SerializedName("date")
    private String date;

    @SerializedName("to")
    private JsonTo to;

    @SerializedName("from")
    private JsonFrom from;
}
