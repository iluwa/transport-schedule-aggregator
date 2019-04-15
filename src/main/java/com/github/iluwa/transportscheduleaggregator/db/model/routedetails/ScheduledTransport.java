package com.github.iluwa.transportscheduleaggregator.db.model.routedetails;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "SCHEDULED_TRANSPORT")
@NoArgsConstructor
public class ScheduledTransport {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "UID", length = 100)
    private String uid;

    @Column(name = "ARRIVAL_DT")
    private LocalDateTime arrivalDate;

    @Column(name = "DEPARTURE_DT")
    private LocalDateTime departureDate;

    @ManyToOne
    @JoinColumn(name = "ROUTE_ID")
    private Route route;

    @OneToMany(mappedBy = "scheduledTransport")
    private List<Thicket> thicket;

    public ScheduledTransport(String title, String uid, LocalDateTime arrivalDate, LocalDateTime departureDate, Route route) {
        this.title = title;
        this.uid = uid;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.route = route;
    }
}
