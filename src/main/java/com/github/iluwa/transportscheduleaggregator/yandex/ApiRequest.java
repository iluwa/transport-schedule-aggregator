package com.github.iluwa.transportscheduleaggregator.yandex;

import com.github.kevinsawicki.http.HttpRequest;

/**
 * Common request interface
 */
public interface ApiRequest {
    HttpRequest sendGetRequest();
}
