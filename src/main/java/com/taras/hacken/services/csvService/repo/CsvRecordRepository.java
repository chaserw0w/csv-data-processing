package com.taras.hacken.services.csvService.repo;

import com.taras.hacken.domain.CsvRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CsvRecordRepository extends JpaRepository<CsvRecord, Long> {

}
