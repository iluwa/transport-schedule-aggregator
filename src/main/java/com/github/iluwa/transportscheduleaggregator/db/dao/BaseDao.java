package com.github.iluwa.transportscheduleaggregator.db.dao;

import java.util.List;

/**
 * Base database operations
 */
public interface BaseDao {
    /**
     * Get entity by id
     * @param clazz - entity class
     * @param id
     * @param <T> - entity type
     * @return entity
     */
    public <T> T getById(Class<T> clazz, long id);

    /**
     * get all table data
     * @param clazz - entity class
     * @param <T> - entity type
     * @return entity list
     */
    public <T> List<T> getAll(Class<T> clazz);

    /**
     * save entity
     * @param entity - new entity to save
     * @param <T> - entity type
     */
    public <T> void save(T entity);

    /**
     * update entity
     * @param entity - detached entity
     * @param <T> - entity type
     */
    public <T> void update(T entity);

    /**
     * remove entity
     * @param entity
     * @param <T>
     */
    public <T> void remove(T entity);

    /**
     * Mehthod deletes data from db
     * @param clazz - entity class
     * @param <T>
     * @return number of deleted entities
     */
    public <T> int deleteAllEntities(Class<T> clazz);

    /**
     * flush all changes to db
     */
    public void flush();

    /**
     * clear session instance
     */
    public void clear();
}
