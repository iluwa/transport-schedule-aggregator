package com.github.iluwa.transportscheduleaggregator.db.model.dictionaries;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "SETTLEMENT")
@NoArgsConstructor
@Getter
@Setter
public class Settlement {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private long id;

    @Column(name = "CODE")
    private String code;

    @Column(name = "TITLE")
    private String title;

    @ManyToOne
    @JoinColumn(name = "REGION_ID")
    private Region region;

    @OneToMany(mappedBy = "settlement")
    private List<Station> stations;

    public Settlement(String code, String title, Region region) {
        this.code = code;
        this.title = title;
        this.region = region;
    }
}
