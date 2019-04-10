package com.github.iluwa.transportscheduleaggregator.service;

import com.github.iluwa.transportscheduleaggregator.db.BaseDao;
import com.github.iluwa.transportscheduleaggregator.db.model.dictionaries.Country;
import com.github.iluwa.transportscheduleaggregator.db.model.dictionaries.Region;
import com.github.iluwa.transportscheduleaggregator.db.model.dictionaries.Settlement;
import com.github.iluwa.transportscheduleaggregator.db.model.dictionaries.Station;
import com.github.iluwa.transportscheduleaggregator.yandex.response.stationsliststructure.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class manage dictionary entities
 */
@Service
@AllArgsConstructor
public class DictionariesService {
    @Autowired
    BaseDao baseDao;

    /**
     * Method maps json object response (stations_list) to entities
     * @param countriesContainer head of json response object
     */
    public void countriesToEntity(CountriesContainer countriesContainer) {
        int count = 0;
        for (JsonCountry jsonCountry : countriesContainer.getCountries()) {
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