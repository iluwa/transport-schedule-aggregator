package com.github.iluwa.transportscheduleaggregator.userinteractionhelper.commandhandlers;

import com.github.iluwa.transportscheduleaggregator.service.DictionariesService;
import com.github.iluwa.transportscheduleaggregator.userinteractionhelper.CommandHandler;
import org.springframework.beans.factory.annotation.Autowired;

public final class IntervalPriceByUidHandler implements CommandHandler {
    private final String uid;
    private final String stationFrom;
    private final String stationTo;
    private final String inputDateFrom;
    private final String inputDateTo;

    @Autowired
    private DictionariesService dictionariesService;

    public IntervalPriceByUidHandler(String uid, String stationFrom, String stationTo, String inputDateFrom, String inputDateTo) {
        this.uid = uid;
        this.stationFrom = stationFrom;
        this.stationTo = stationTo;
        this.inputDateFrom = inputDateFrom;
        this.inputDateTo = inputDateTo;
    }

    // TODO
    @Override
    public void handle() {

    }
}
