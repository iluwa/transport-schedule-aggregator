package com.github.iluwa.transportscheduleaggregator.service;

import com.github.iluwa.transportscheduleaggregator.db.dao.BaseDao;
import com.github.iluwa.transportscheduleaggregator.db.dao.DictionaryDao;
import com.github.iluwa.transportscheduleaggregator.db.model.dictionaries.*;
import com.github.iluwa.transportscheduleaggregator.yandex.stationslist.responsestructure.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * Class manages dictionary entities
 */
@Service
public class DictionariesService {

    private final static Logger LOGGER = LoggerFactory.getLogger(DictionariesService.class);

    @Autowired
    private BaseDao baseDao;

    @Autowired
    private DictionaryDao dictionaryDao;

    private int count;
    /**
     * Method maps json object response (stations_list) to entity
     * @param stationsListResponse head of json response object
     *
     * TODO: See if i can split mapping logic and write to db logic
     */
    public void stationsListResponseToEntity(StationsListResponse stationsListResponse) {
        LOGGER.info("Saving stationslist response to database");
        count = 0;
        for (JsonCountry jsonCountry : stationsListResponse.getCountries()) {
            saveStationObjectToDatabase(jsonCountry);
            for (JsonRegion jsonRegion : jsonCountry.getRegions()) {
                saveStationObjectToDatabase(jsonRegion);
                for (JsonSettlement jsonSettlement : jsonRegion.getSettlements()) {
                    saveStationObjectToDatabase(jsonSettlement);
                    jsonSettlement.getStations().forEach(this::saveStationObjectToDatabase);
                }
            }
        }
        baseDao.save(new DictionaryActuality());
        LOGGER.info("Saved " + count + " stations records");
    }

    private void saveStationObjectToDatabase(StationObject obj) {
        String title = Optional.ofNullable(obj.getTitle())
                .map(String::toLowerCase)
                .orElse("");

        String code = Optional.ofNullable(obj.getCode())
                .map(JsonCode::getCode)
                .orElse("");

        if (!title.isEmpty() && !code.isEmpty() ) {
            StationsDictionary station = new StationsDictionary(code, title);
            baseDao.save(station);
            count++;
            if (count % 50 == 0) {
                baseDao.flush();
                baseDao.clear();
            }
        }
    }

    @Transactional
    public void clearDictionary() {
        LOGGER.info("Clearing dictionaries");
        baseDao.deleteAllEntities(StationsDictionary.class);
        baseDao.deleteAllEntities(DictionaryActuality.class);
        baseDao.flush();
    }

    /**
     * @return true if dictionary was loaded, otherwise false
     */
    public boolean isDictionaryLoaded() {
        return baseDao.getAll(DictionaryActuality.class).stream().count() > 0;
    }

    /**
     * @param title - station name
     * @return station code
     */
    public String getCodeByTitle(@NonNull String title) {
        return Optional.ofNullable(dictionaryDao.getStationByTitle(title.toLowerCase()))
                .map(StationsDictionary::getCode)
                .orElse("");
    }
}
