package com.github.iluwa.transportscheduleaggregator.db.dao.impl;

import com.github.iluwa.transportscheduleaggregator.db.dao.BaseDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.transaction.Transactional;
import java.util.List;


@Repository
@Transactional
public class BaseDaoImpl implements BaseDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public <T> T getById(Class<T> clazz, long id) {
        return entityManager.find(clazz, id);
    }

    @Override
    public <T> List<T> getAll(Class<T> clazz) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT t FROM ").append(clazz.getSimpleName()).append(" t");
        Query query = entityManager.createQuery(sb.toString());
        return query.getResultList();
    }

    @Override
    public <T> void save(T entity) {
        entityManager.persist(entity);
    }

    @Override
    public <T> void update(T entity) {
        entityManager.merge(entity);
    }

    @Override
    public <T> void remove(T entity) {
        if (!entityManager.contains(entity)) {
            entity = entityManager.merge(entity);
        }
        entityManager.remove(entity);
    }

    @Override
    public <T> int deleteAllEntities(Class<T> clazz) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaDelete<T> query = cb.createCriteriaDelete(clazz);
        query.from(clazz);
        return entityManager.createQuery(query).executeUpdate();
    }

    @Override
    public void flush() {
        entityManager.flush();
    }

    @Override
    public void clear() {
        entityManager.clear();
    }
}
