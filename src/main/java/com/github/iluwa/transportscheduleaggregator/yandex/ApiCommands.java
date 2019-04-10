package com.github.iluwa.transportscheduleaggregator.yandex;

import com.github.kevinsawicki.http.HttpRequest;

import java.util.Map;

/**
 * Interface to make different yandex API calls
 */
public interface ApiCommands {
    /**
     * Search routes schedule from one station to another
     * @param params - input map with parameters to get request
     */
    public HttpRequest search(Map<String, String> params);

    /**
     * Query all stations with codes
     * @param params - input map with parameters to get request
     */
    public HttpRequest stationsList(Map<String, String> params);
}
