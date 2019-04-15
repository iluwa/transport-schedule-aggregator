package com.github.iluwa.transportscheduleaggregator.db.model.dictionaries;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "STATION")
@NoArgsConstructor
@Getter
@Setter
public class Station {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private long id;

    @Column(name = "CODE", length = 50)
    private String code;

    @Column(name = "TITLE")
    private String title;

    @ManyToOne
    @JoinColumn(name = "SETTLEMENT_ID")
    private Settlement settlement;

    public Station(String code, String title, Settlement settlement) {
        this.code = code;
        this.title = title;
        this.settlement = settlement;
    }
}
