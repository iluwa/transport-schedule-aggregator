package com.github.iluwa.transportscheduleaggregator.db.model.RouteDetails;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @Column(name = "UID")
    private String uid;

    @Column(name = "ARRIVAL_DT")
    private LocalDateTime arrivalDate;

    @Column(name = "DEPARTURE_DT")
    private LocalDateTime departureDate;

    @ManyToOne
    @JoinColumn(name = "ROUTE_ID")
    private Route rooute;

    @OneToMany(mappedBy = "scheduledTransport")
    private Thicket thicket;
}
