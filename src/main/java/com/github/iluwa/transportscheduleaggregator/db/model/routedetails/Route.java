package com.github.iluwa.transportscheduleaggregator.db.model.routedetails;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Table stores results of search request and their invocation datetime
 */
@Entity
@Table(name = "ROUTE")
@NoArgsConstructor
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private long id;

    @Column(name = "CODE_FROM", length = 50)
    private String codeFrom;

    @Column(name = "CODE_TO", length = 50)
    private String codeTo;

    @Column(name = "ROUTE_DT")
    private LocalDate routeDate;

    @Column(name = "CREATED_DT")
    @CreationTimestamp
    private LocalDateTime createdDate;

    @OneToMany(mappedBy = "route")
    private List<ScheduledTransport> scheduledTransport;

    public Route(String codeFrom, String codeTo, LocalDate routeDate) {
        this.codeFrom = codeFrom;
        this.codeTo = codeTo;
        this.routeDate = routeDate;
    }
}
