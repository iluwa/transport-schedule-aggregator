package com.github.iluwa.transportscheduleaggregator.yandex.search.responsestructure;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class JsonThicketsInfo {
    @SerializedName("et_marker")
    private boolean etMarker;

    @SerializedName("places")
    List<JsonThicket> thicket;
}
