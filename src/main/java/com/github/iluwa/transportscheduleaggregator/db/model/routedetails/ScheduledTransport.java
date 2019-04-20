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

    @OneToMany(mappedBy = "scheduledTransport", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Thicket> thickets;

    public ScheduledTransport(String title, String uid, LocalDateTime arrivalDate, LocalDateTime departureDate, Route route) {
        this.title = title;
        this.uid = uid;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.route = route;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Route{")
                .append("id=").append(id).append(", ")
                .append("title='").append(title).append("', ")
                .append("uid='").append(uid).append("', ")
                .append("arrivalDate=").append(arrivalDate).append(", ")
                .append("departureDate=").append(departureDate).append(", ")
                .append("thickets = [");
        for (Thicket thicket : thickets) {
            sb.append("\n\t\t\t").append(thicket);
        }
        sb.append("\n\t\t]}");
        return sb.toString();
    }
}
