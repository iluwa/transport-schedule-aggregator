package com.github.iluwa.transportscheduleaggregator.db.model.dictionaries;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "STATIONS")
@NoArgsConstructor
@Getter
@Setter
public class StationsDictionary {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private long id;

    @Column(name = "CODE", length = 50)
    private String code;

    @Column(name = "TITLE")
    private String title;

    public StationsDictionary(String code, String title) {
        this.code = code;
        this.title = title;
    }
}
