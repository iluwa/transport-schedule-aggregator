package com.github.iluwa.transportscheduleaggregator.db.dao;

import com.github.iluwa.transportscheduleaggregator.db.model.routedetails.Route;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface RouteDao {
    int deleteOutdatedRoutes(LocalDateTime treshholdDate);

    Route findActualRouteByParams(String codeFrom, String codeTo, LocalDate date, LocalDateTime thresholdDate);
}
