package com.github.iluwa.transportscheduleaggregator.userinteractionhelper;

public enum Commands {
    QUIT (""),
    ROUTES_BY_DATE ("Example: routesByDate Москва Владимир 24.04.2019"),
    INTREVAL_PRICE_BY_UID ("Example: intervalPriceByUidHandler SU-1827A_c26_agent Москва Владимир 24.04.2019 28.04.2019"),
    FIND_STATION_CODE ("Example: findStationCode москва"),
    EMPTY ("");

    private final String example;

    Commands(String example) {
        this.example = example;
    }

    public String getExample() {
        return example;
    }
}
