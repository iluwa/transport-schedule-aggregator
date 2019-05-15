package com.github.iluwa.transportscheduleaggregator.yandex.stationslist;

import com.github.iluwa.transportscheduleaggregator.yandex.AbstractRequest;

import java.util.HashMap;
import java.util.Map;

public class StationsListRequest extends AbstractRequest {
    private static final String REQUEST_NAME = "stations_list";

    private final String apiKey;
    private final String lang;
    private final String format;

    @Override
    protected String getRequestName() {
        return REQUEST_NAME;
    }

    private StationsListRequest(Builder builder) {
        this.apiKey = builder.apiKey;
        this.lang = builder.lang;
        this.format = builder.format;
    }

    @Override
    protected Map<String, String> toMap() {
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put("apikey", apiKey);
        if (!lang.isEmpty()) {
            requestParams.put("lang", lang);
        }
        if (!format.isEmpty()) {
            requestParams.put("format", format);
        }
        return requestParams;
    }

    public static class Builder {
        // Required parameters
        private final String apiKey;

        // Optional parameters
        private String lang = "";
        private String format = "";

        public Builder(String apiKey) {
            this.apiKey = apiKey;
        }

        public Builder setLang(String lang) {
            this.lang = lang;
            return this;
        }

        public Builder setFormat(String format) {
            this.format = format;
            return this;
        }

        public StationsListRequest build() {
            return new StationsListRequest(this);
        }
    }
}
