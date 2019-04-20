package com.github.iluwa.transportscheduleaggregator.db.dao.impl;

import com.github.iluwa.transportscheduleaggregator.db.dao.DictionaryDao;
import com.github.iluwa.transportscheduleaggregator.db.model.dictionaries.StationsDictionary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

@Repository
@Transactional
public class DictionaryDaoImpl implements DictionaryDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public StationsDictionary getStationByTitle(String title) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<StationsDictionary> query = cb.createQuery(StationsDictionary.class);
        Root<StationsDictionary> root = query.from(StationsDictionary.class);
        query.select(root);
        query.where(cb.equal(root.get("title"), title));
        return entityManager.createQuery(query).getResultStream()
                .findFirst().orElse(null);
    }
}
