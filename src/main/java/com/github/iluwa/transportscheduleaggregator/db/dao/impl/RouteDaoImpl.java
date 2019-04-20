package com.github.iluwa.transportscheduleaggregator.db.dao.impl;

import com.github.iluwa.transportscheduleaggregator.db.dao.RouteDao;
import com.github.iluwa.transportscheduleaggregator.db.model.routedetails.Route;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository
@Transactional
public class RouteDaoImpl implements RouteDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public int deleteOutdatedRoutes(LocalDateTime thresholdDate) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaDelete<Route> query = cb.createCriteriaDelete(Route.class);
        Root<Route> root = query.from(Route.class);
        query.where(cb.lessThan(root.get("createdDate"), thresholdDate));
        return entityManager.createQuery(query).executeUpdate();
    }

    @Override
    public Route findActualRouteByParams(String codeFrom, String codeTo, LocalDate date, LocalDateTime thresholdDate) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Route> query = cb.createQuery(Route.class);
        Root<Route> root = query.from(Route.class);
        query.select(root);
        query.where(cb.and(
                cb.equal(root.get("codeFrom"), codeFrom),
                cb.equal(root.get("codeTo"), codeTo),
                cb.equal(root.get("routeDate"), date),
                cb.greaterThan(root.get("createdDate"), thresholdDate)));
        return entityManager.createQuery(query).getResultStream()
                .findFirst().orElse(null);
    }
}
