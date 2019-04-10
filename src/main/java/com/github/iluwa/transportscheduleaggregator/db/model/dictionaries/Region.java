package com.github.iluwa.transportscheduleaggregator.db.model.dictionaries;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "REGION")
@NoArgsConstructor
@Getter
@Setter
public class Region {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private long id;

    @Column(name = "CODE")
    private String code;

    @Column(name = "TITLE")
    private String title;

    @ManyToOne
    @JoinColumn(name = "COUNTRY_ID")
    private Country country;

    @OneToMany(mappedBy = "region")
    private List<Settlement> settlements;

    public Region(String code, String title, Country country) {
        this.code = code;
        this.title = title;
        this.country = country;
    }
}
