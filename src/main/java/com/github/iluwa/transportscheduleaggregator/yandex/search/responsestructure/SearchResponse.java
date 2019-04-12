package com.github.iluwa.transportscheduleaggregator.yandex.search.responsestructure;

import com.github.iluwa.transportscheduleaggregator.yandex.HeadOfResponse;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * This class is head of Search response
 * Used to deserialize String response to pojo objects
 */
@NoArgsConstructor
@Getter
public class SearchResponse implements HeadOfResponse {
    @SerializedName("pagination")
    private JsonPagination pagination;

    @SerializedName("interval_segments")
    private List<JsonSegment> intervalSegments;

    @SerializedName("segments")
    private List<JsonSegment> segments;

    @SerializedName("search")
    private JsonSearchParams searchParams;
}
