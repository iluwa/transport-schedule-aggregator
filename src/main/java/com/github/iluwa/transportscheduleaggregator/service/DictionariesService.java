package com.github.iluwa.transportscheduleaggregator.service;

import com.github.iluwa.transportscheduleaggregator.db.BaseDao;
import com.github.iluwa.transportscheduleaggregator.db.model.dictionaries.Country;
import com.github.iluwa.transportscheduleaggregator.db.model.dictionaries.Region;
import com.github.iluwa.transportscheduleaggregator.db.model.dictionaries.Settlement;
import com.github.iluwa.transportscheduleaggregator.db.model.dictionaries.Station;
import com.github.iluwa.transportscheduleaggregator.yandex.stationslist.responsestructure.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Class manages dictionary entities
 */
@Service
@AllArgsConstructor
public class DictionariesService {
    @Autowired
    BaseDao baseDao;

    /**
     * Method maps json object response (stations_list) to entities
     * @param stationsListResponse head of json response object
     *
     * TODO: See if i can split mapping logic and write to db logic
     */
    public void stationsListResponseToEntity(StationsListResponse stationsListResponse) {
        int count = 0;
        for (JsonCountry jsonCountry : stationsListResponse.getCountries()) {
            Country country = new Country(jsonCountry.getCode().getCode(), jsonCountry.getTitle());
            baseDao.save(country);
            for (JsonRegion jsonRegion : jsonCountry.getRegions()) {
                Region region = new Region(jsonRegion.getCode().getCode(), jsonRegion.getTitle(), country);
                baseDao.save(region);
                for (JsonSettlement jsonSettlement : jsonRegion.getSettlements()) {
                    Settlement settlement = new Settlement(jsonSettlement.getCode().getCode(),
                            jsonSettlement.getTitle(), region);
                    baseDao.save(settlement);
                    for (JsonStation jsonStation : jsonSettlement.getStations()) {
                        Station station = new Station(jsonStation.getCode().getCode(),
                                jsonStation.getTitle(), settlement);
                        baseDao.save(station);

                        if (++count % 50 == 0) {
                            baseDao.flush();
                            baseDao.clear();
                        }
                    }
                }
            }
        }
    }

    public void clearDictionaries() {
        baseDao.deleteAllEntities(Station.class);
        baseDao.deleteAllEntities(Settlement.class);
        baseDao.deleteAllEntities(Region.class);
        baseDao.deleteAllEntities(Country.class);
        baseDao.flush();
    }
}
