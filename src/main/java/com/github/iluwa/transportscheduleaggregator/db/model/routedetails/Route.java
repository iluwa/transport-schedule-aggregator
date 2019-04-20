package com.github.iluwa.transportscheduleaggregator.db.model.routedetails;

import lombok.Getter;
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
@Getter
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

    @OneToMany(mappedBy = "route", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<ScheduledTransport> scheduledTransports;

    public Route(String codeFrom, String codeTo, LocalDate routeDate) {
        this.codeFrom = codeFrom;
        this.codeTo = codeTo;
        this.routeDate = routeDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Route{")
                .append("id=").append(id).append(", ")
                .append("codeFrom='").append(codeFrom).append("', ")
                .append("codeTo='").append(codeTo).append("', ")
                .append("routeDate=").append(routeDate).append(", ")
                .append("createdDate=").append(createdDate).append(", ")
                .append("scheduledTransports = [");
        for (ScheduledTransport transport : scheduledTransports) {
            sb.append("\n\t\t").append(transport);
        }
        sb.append("\n\t]}");

        return sb.toString();
    }
}
