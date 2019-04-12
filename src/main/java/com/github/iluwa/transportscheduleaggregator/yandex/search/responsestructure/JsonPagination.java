package com.github.iluwa.transportscheduleaggregator.yandex.search.responsestructure;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class JsonPagination {

    @SerializedName("total")
    private int total;

    @SerializedName("limit")
    private int limit;

    @SerializedName("offset")
    private int offset;
}
