package com.github.iluwa.transportscheduleaggregator.service;

import com.github.iluwa.transportscheduleaggregator.db.BaseDao;
import com.github.iluwa.transportscheduleaggregator.db.model.routedetails.Route;
import com.github.iluwa.transportscheduleaggregator.db.model.routedetails.ScheduledTransport;
import com.github.iluwa.transportscheduleaggregator.db.model.routedetails.Thicket;
import com.github.iluwa.transportscheduleaggregator.yandex.search.responsestructure.JsonSegment;
import com.github.iluwa.transportscheduleaggregator.yandex.search.responsestructure.JsonThicket;
import com.github.iluwa.transportscheduleaggregator.yandex.search.responsestructure.SearchResponse;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class RouteService {
    private final static DateTimeFormatter DATE_WITHOUT_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final static DateTimeFormatter FULL_DATE_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssxxx");

    @Autowired
    BaseDao baseDao;

    /**
     * Method maps json object response (search) to entities
     * @param searchResponse head of json response object
     *
     * TODO: See if i can split mapping logic and write to db logic
     */
    public void searchResponseToEntity(SearchResponse searchResponse) {
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
}
