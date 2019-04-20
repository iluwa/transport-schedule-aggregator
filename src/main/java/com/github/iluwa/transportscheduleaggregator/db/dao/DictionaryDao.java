package com.github.iluwa.transportscheduleaggregator.db.dao;

import com.github.iluwa.transportscheduleaggregator.db.model.dictionaries.StationsDictionary;

public interface DictionaryDao {
    StationsDictionary getStationByTitle(String title);
}
