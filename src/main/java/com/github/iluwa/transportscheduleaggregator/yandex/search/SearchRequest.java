package com.github.iluwa.transportscheduleaggregator.yandex.search;

import com.github.iluwa.transportscheduleaggregator.yandex.AbstractRequest;

import java.util.HashMap;
import java.util.Map;

public class SearchRequest extends AbstractRequest {
    private static final String REQUEST_NAME = "search";

    private final String apiKey;
    private final String from;
    private final String to;
    private final String lang;
    private final String format;
    private final String date;
    private final String transportType;
    private final String system;
    private final String showSystems;
    private final Integer offset;
    private final Integer limit;
    private final Boolean addDaysMask;
    private final String resultTimeZone;
    private final Boolean transfers;

    @Override
    protected String getRequestName() {
        return REQUEST_NAME;
    }

    private SearchRequest(SearchRequest.Builder builder) {
        this.apiKey = builder.apiKey;
        this.from = builder.from;
        this.to = builder.to;
        this.lang = builder.lang;
        this.format = builder.format;
        this.date = builder.date;
        this.transportType = builder.transportType;
        this.system = builder.system;
        this.showSystems = builder.showSystems;
        this.offset = builder.offset;
        this.limit = builder.limit;
        this.addDaysMask = builder.addDaysMask;
        this.resultTimeZone = builder.resultTimeZone;
        this.transfers = builder.transfers;
    }

    @Override
    protected Map<String, String> toMap() {
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put("apikey", apiKey);
        requestParams.put("from", from);
        requestParams.put("to", to);
        if (!lang.isEmpty()) {
            requestParams.put("lang", lang);
        }
        if (!format.isEmpty()) {
            requestParams.put("format", format);
        }
        if (!date.isEmpty()) {
            requestParams.put("date", date);
        }
        if (!transportType.isEmpty()) {
            requestParams.put("transport_types", transportType);
        }
        if (!system.isEmpty()) {
            requestParams.put("system", system);
        }
        if (!showSystems.isEmpty()) {
            requestParams.put("show_systems", showSystems);
        }
        if (offset != null) {
            requestParams.put("offset", offset.toString());
        }
        if (limit != null) {
            requestParams.put("limit", limit.toString());
        }
        if (addDaysMask != null) {
            requestParams.put("add_days_mask", addDaysMask.toString());
        }
        if (!resultTimeZone.isEmpty()) {
            requestParams.put("result_timezone", resultTimeZone);
        }
        if (transfers != null) {
            requestParams.put("transfers", transfers.toString());
        }
        return requestParams;
    }

    public static class Builder {
        // Required parameters
        private final String apiKey;
        private final String from;
        private final String to;

        // Optional parameters
        private String lang = "";
        private String format = "";
        private String date = "";
        private String transportType = "";
        private String system = "";
        private String showSystems= "";
        private Integer offset;
        private Integer limit;
        private Boolean addDaysMask;
        private String resultTimeZone = "";
        private Boolean transfers;

        public Builder(String apiKey, String from, String to) {
            this.apiKey = apiKey;
            this.from = from;
            this.to = to;
        }

        public Builder setDate(String date) {
            this.date = date;
            return this;
        }

        public Builder setTransportType(String transportType) {
            this.transportType = transportType;
            return this;
        }

        public Builder setSystem(String system) {
            this.system = system;
            return this;
        }

        public Builder setShowSystems(String showSystems) {
            this.showSystems = showSystems;
            return this;
        }

        public Builder setOffset(Integer offset) {
            this.offset = offset;
            return this;
        }

        public Builder setLimit(Integer limit) {
            this.limit = limit;
            return this;
        }

        public Builder setAddDaysMask(Boolean addDaysMask) {
            this.addDaysMask = addDaysMask;
            return this;
        }

        public Builder setResultTimeZone(String resultTimeZone) {
            this.resultTimeZone = resultTimeZone;
            return this;
        }

        public Builder setTransfers(Boolean transfers) {
            this.transfers = transfers;
            return this;
        }

        public SearchRequest build() {
            return new SearchRequest(this);
        }
    }
}
