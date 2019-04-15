package com.github.iluwa.transportscheduleaggregator.db.model.routedetails;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "THICKET")
@NoArgsConstructor
public class Thicket {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private long id;

    @Column(name = "PRICE")
    private double price;

    @Column(name = "NAME")
    private String name;

    @ManyToOne
    @JoinColumn(name = "SCHEDULED_TRANSPORT_ID")
    private ScheduledTransport scheduledTransport;

    public Thicket(double price, String name, ScheduledTransport scheduledTransport) {
        this.price = price;
        this.name = name;
        this.scheduledTransport = scheduledTransport;
    }
}
