package com.github.iluwa.transportscheduleaggregator.yandex.search.responsestructure;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class JsonPrice {
    @SerializedName("cents")
    private int cents;

    @SerializedName("whole")
    private int whole;
}
