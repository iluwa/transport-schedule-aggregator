package com.github.iluwa.transportscheduleaggregator.userinteractionhelper;

import com.github.iluwa.transportscheduleaggregator.userinteractionhelper.exceptions.CodeNotFoundException;
import com.github.iluwa.transportscheduleaggregator.userinteractionhelper.exceptions.HttpException;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeParseException;

@Component
public interface CommandHandler {
    void handle() throws CodeNotFoundException, DateTimeParseException, HttpException;
}
