package com.github.iluwa.transportscheduleaggregator.yandex;

import com.github.kevinsawicki.http.HttpRequest;
import lombok.AllArgsConstructor;

import java.util.Map;

public class ApiCommandsImpl implements ApiCommands {
    public final static String API_URL = "https://api.rasp.yandex.net/v3.0/";
    public final static String SEARCH_COMMAND_NAME = "search";
    public final static String STATIONS_LIST = "stations_list";

    /**
     * Required yandex-rasp developer api key to work
     */
    private final String apiKey;

    public ApiCommandsImpl(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public HttpRequest search(Map<String, String> params) {
        params.put("apikey", apiKey);
        return HttpRequest.get(API_URL + SEARCH_COMMAND_NAME, params, true);
    }

    @Override
    public HttpRequest stationsList(Map<String, String> params) {
        params.put("apikey", apiKey);
        return HttpRequest.get(API_URL + STATIONS_LIST, params, true);
    }
}
