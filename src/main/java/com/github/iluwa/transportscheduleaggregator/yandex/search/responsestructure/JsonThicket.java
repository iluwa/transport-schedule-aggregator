package com.github.iluwa.transportscheduleaggregator.yandex.search.responsestructure;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class JsonThicket {
    @SerializedName("currency")
    private String currency;

    @SerializedName("price")
    private JsonPrice price;

    @SerializedName("name")
    private String name;
}
