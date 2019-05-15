package com.github.iluwa.transportscheduleaggregator.service;

import com.github.iluwa.transportscheduleaggregator.db.dao.BaseDao;
import com.github.iluwa.transportscheduleaggregator.db.dao.RouteDao;
import com.github.iluwa.transportscheduleaggregator.db.model.routedetails.Route;
import com.github.iluwa.transportscheduleaggregator.db.model.routedetails.ScheduledTransport;
import com.github.iluwa.transportscheduleaggregator.db.model.routedetails.Thicket;
import com.github.iluwa.transportscheduleaggregator.yandex.search.responsestructure.JsonSegment;
import com.github.iluwa.transportscheduleaggregator.yandex.search.responsestructure.JsonThicket;
import com.github.iluwa.transportscheduleaggregator.yandex.search.responsestructure.SearchResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * Class manages route entities
 */
@Service
public class RouteService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RouteService.class);
    private static final DateTimeFormatter DATE_WITHOUT_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter FULL_DATE_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssxxx");
    private static final int ROUTE_RESPONSE_ACTUALITY_HOURS = 24;

    @Autowired
    private BaseDao baseDao;

    @Autowired
    private RouteDao routeDao;

    /**
     * Method maps json object response (search) to entities
     * @param searchResponse head of json response object
     *
     * TODO: See if i can split mapping logic and write to db logic
     */
    public void searchResponseToEntity(SearchResponse searchResponse) {
        LOGGER.info("Saving search reponse to database");

        String date = Optional.ofNullable(searchResponse.getSearchParams().getDate()).orElse("");
        LocalDate requestedDate = !date.isEmpty() ? LocalDate.parse(date, DATE_WITHOUT_TIME) : null;

        Route route = new Route(
                Optional.ofNullable(searchResponse.getSearchParams().getFrom().getCode()).orElse(""),
                Optional.ofNullable(searchResponse.getSearchParams().getTo().getCode()).orElse(""),
                requestedDate);
        baseDao.save(route);

        for (JsonSegment segment : searchResponse.getSegments()) {
            StringBuilder title = new StringBuilder();
            title.append(Optional.ofNullable(segment.getThread().getNumber()).orElse(""))
                    .append(" ")
                    .append(Optional.ofNullable(segment.getThread().getTitle()).orElse(""))
                    .append(" ")
                    .append(Optional.ofNullable(segment.getThread().getTransportType()).orElse(""));

            ScheduledTransport scheduledTransport = new ScheduledTransport(
                    title.toString(),
                    Optional.ofNullable(segment.getThread().getUid()).orElse(""),
                    LocalDateTime.parse(segment.getArrival(), FULL_DATE_TIME),
                    LocalDateTime.parse(segment.getDeparture(), FULL_DATE_TIME),
                    route);
            baseDao.save(scheduledTransport);

            for (JsonThicket ticketInfo : segment.getThicketsInfo().getThicket()) {
                Thicket thicket = new Thicket(
                        ticketInfo.getPrice().getWhole() + ((double) ticketInfo.getPrice().getCents() / 100),
                        Optional.ofNullable(ticketInfo.getName()).orElse(""),
                        scheduledTransport);
                baseDao.save(thicket);
            }
        }
        baseDao.flush();
        baseDao.clear();
    }

    /**
     * Method deletes all outdated routes
     */
    public void deleteOutdatedRoutes() {
        LOGGER.info("Clearing outdated routes");
        routeDao.deleteOutdatedRoutes(LocalDateTime.now().minusHours(ROUTE_RESPONSE_ACTUALITY_HOURS));
    }

    public Route findActualRouteByParams(String codeFrom, String codeTo, LocalDate date) {
        return routeDao.findActualRouteByParams(codeFrom, codeTo, date,
                LocalDateTime.now().minusHours(ROUTE_RESPONSE_ACTUALITY_HOURS));
    }
}
