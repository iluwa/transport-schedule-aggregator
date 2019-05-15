package com.github.iluwa.transportscheduleaggregator.userinteractionhelper.commandhandlers;

import com.github.iluwa.transportscheduleaggregator.db.model.routedetails.Route;
import com.github.iluwa.transportscheduleaggregator.service.DictionariesService;
import com.github.iluwa.transportscheduleaggregator.service.RouteService;
import com.github.iluwa.transportscheduleaggregator.userinteractionhelper.exceptions.CodeNotFoundException;
import com.github.iluwa.transportscheduleaggregator.userinteractionhelper.CommandHandler;
import com.github.iluwa.transportscheduleaggregator.userinteractionhelper.exceptions.HttpException;
import com.github.iluwa.transportscheduleaggregator.yandex.ApiKeyStorage;
import com.github.iluwa.transportscheduleaggregator.yandex.search.SearchRequest;
import com.github.iluwa.transportscheduleaggregator.yandex.search.responsestructure.SearchResponse;
import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.Gson;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class RoutesByDateHandler implements CommandHandler {
    private static final DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final DateTimeFormatter API_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final String stationFrom;
    private final String stationTo;
    private final String inputDate;
    private final DictionariesService dictionariesService;
    private final RouteService routeService;

    public RoutesByDateHandler(String stationFrom, String stationTo, String inputDate, DictionariesService dictionariesService, RouteService routeService) {
        this.stationFrom = stationFrom;
        this.stationTo = stationTo;
        this.inputDate = inputDate;
        this.dictionariesService = dictionariesService;
        this.routeService = routeService;
    }

    @Override
    public void handle() throws CodeNotFoundException, DateTimeParseException, HttpException {
        String codeFrom = dictionariesService.getCodeByTitle(stationFrom);
        String codeTo = dictionariesService.getCodeByTitle(stationTo);
        if (codeFrom.isEmpty() || codeTo.isEmpty()) {
            throw new CodeNotFoundException();
        }
        LocalDate date = LocalDate.parse(inputDate, INPUT_DATE_FORMAT);
        Route route = routeService.findActualRouteByParams(codeFrom, codeTo, date);

        if (route == null) {
            SearchRequest.Builder builder =
                    new SearchRequest.Builder(ApiKeyStorage.YANDEX_API_KEY, codeFrom, codeTo);
            builder.setDate(date.format(API_DATE_FORMAT));
            SearchRequest req = builder.build();
            HttpRequest resp = req.sendGetRequest();
            if (resp.code() != 200) {
                throw new HttpException("Http error code: " + resp.code());
            }
            Gson gson = new Gson();
            SearchResponse response = gson.fromJson(resp.body(), SearchResponse.class);
            routeService.searchResponseToEntity(response);
            route = routeService.findActualRouteByParams(codeFrom, codeTo, date);
        }

        System.out.println(route);
    }
}
