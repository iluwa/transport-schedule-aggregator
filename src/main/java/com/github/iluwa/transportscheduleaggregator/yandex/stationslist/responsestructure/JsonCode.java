package com.github.iluwa.transportscheduleaggregator.yandex.stationslist.responsestructure;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;


/**
 * This class is part Codes of response
 * Used to deserialize String response to pojo objects
 */
@NoArgsConstructor
@Getter
public class JsonCode {
    @SerializedName("yandex_code")
    private String code;
}
