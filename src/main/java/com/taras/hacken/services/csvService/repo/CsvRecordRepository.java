package com.taras.hacken.services.csvService.repo;

import com.taras.hacken.domain.CsvRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CsvRecordRepository extends JpaRepository<CsvRecord, Long> {

    @Query(value = "SELECT * FROM csv_record c JOIN csv_record_data d " +
            "ON c.id = d.csv_record_id " +
            "WHERE LOWER(d.column_value) LIKE LOWER(CONCAT('%', :keyword, '%'))",
            nativeQuery = true)
    List<CsvRecord> search(@Param("keyword") String keyword);
}
