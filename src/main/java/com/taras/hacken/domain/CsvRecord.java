package com.taras.hacken.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Map;

@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode
@Setter
@Getter
public class CsvRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @CollectionTable(name = "csv_record_data", joinColumns = @JoinColumn(name = "csv_record_id"))
    @MapKeyColumn(name = "column_name")
    @Column(name = "column_value")
    private Map<String, String> data;
}
