package com.github.iluwa.transportscheduleaggregator.db.model.dictionaries;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "COUNTRY")
@NoArgsConstructor
@Getter
@Setter
public class Country {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private long id;

    @Column(name = "CODE")
    private String code;

    @Column(name = "TITLE")
    private String title;

    @OneToMany(mappedBy = "country")
    private List<Region> regions;

    public Country(String code, String title) {
        this.code = code;
        this.title = title;
    }
}
