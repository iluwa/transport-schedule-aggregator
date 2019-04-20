package com.github.iluwa.transportscheduleaggregator.userinteractionhelper.commandhandlers;

import com.github.iluwa.transportscheduleaggregator.service.DictionariesService;
import com.github.iluwa.transportscheduleaggregator.userinteractionhelper.CommandHandler;
import com.github.iluwa.transportscheduleaggregator.userinteractionhelper.exceptions.CodeNotFoundException;
import com.github.iluwa.transportscheduleaggregator.userinteractionhelper.exceptions.HttpException;

import java.time.format.DateTimeParseException;

public class FindStationCodeHandler implements CommandHandler {
    private final String stationName;
    private final DictionariesService dictionariesService;

    public FindStationCodeHandler(String stationName, DictionariesService dictionariesService) {
        this.stationName = stationName;
        this.dictionariesService = dictionariesService;
    }

    @Override
    public void handle() throws CodeNotFoundException, DateTimeParseException, HttpException {
        System.out.println(dictionariesService.getCodeByTitle(stationName));
    }
}
