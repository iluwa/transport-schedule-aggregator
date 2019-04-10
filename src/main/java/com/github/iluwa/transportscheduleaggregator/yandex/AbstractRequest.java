package com.github.iluwa.transportscheduleaggregator.yandex;

import com.github.kevinsawicki.http.HttpRequest;

import java.util.Map;

/**
 * Abstract class for api request
 *
 */
public abstract class AbstractRequest implements ApiRequest {
    public final static String API_URL = "https://api.rasp.yandex.net/v3.0/";

    /**
     * @return name of current request
     */
    protected abstract String getRequestName();

    @Override
    public HttpRequest sendGetRequest() {
        Map<String, String> requestParams = toMap();
        return HttpRequest.get(API_URL + getRequestName(), requestParams, true);
    }

    /**
     * Method converts current request to map
     * @return map of params to send
     */
    protected abstract Map<String, String> toMap();
}
