package com.github.iluwa.transportscheduleaggregator.db.model.RouteDetails;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

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

    @Column(name = "CODE_FROM")
    private String codeFrom;

    @Column(name = "CODE_TO")
    private String codeTo;

    @Column(name = "ROUTE_DT")
    private LocalDate routeDate;

    @Column(name = "CREATED_DT")
    @CreationTimestamp
    private LocalDateTime createdDate;

    @OneToMany(mappedBy = "route")
    private ScheduledTransport scheduledTransport;
}
