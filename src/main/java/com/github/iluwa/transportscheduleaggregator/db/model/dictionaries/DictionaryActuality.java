package com.github.iluwa.transportscheduleaggregator.db.model.dictionaries;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "DICTIONARY_ACTUALITY")
@NoArgsConstructor
@Getter
@Setter
public class DictionaryActuality {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private long id;

    @Column(name = "CREATED_DT")
    @CreationTimestamp
    private LocalDateTime createdDate;
}
