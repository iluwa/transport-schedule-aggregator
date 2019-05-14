package com.github.iluwa.transportscheduleaggregator.db.dao;

import com.github.iluwa.transportscheduleaggregator.db.model.routedetails.Route;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface RouteDao {
    int deleteOutdatedRoutes(LocalDateTime thresholdDate);

    Route findActualRouteByParams(String codeFrom, String codeTo, LocalDate date, LocalDateTime thresholdDate);
}
