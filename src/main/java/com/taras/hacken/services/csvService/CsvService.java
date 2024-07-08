package com.taras.hacken.services.csvService;

import com.taras.hacken.domain.CsvRecord;
import com.taras.hacken.services.csvService.repo.CsvRecordRepository;
import io.micrometer.core.instrument.Counter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class CsvService {

    private final CsvRecordRepository csvRecordRepository;
    private final Counter csvUploadCounter;

    public void uploadCsv(MultipartFile file, boolean hasHeader, String delimiter) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))){
            String line;
            String[] headers = null;
            if (hasHeader && (line = reader.readLine()) != null) {
                headers = line.split(delimiter);
            }
            while ((line = reader.readLine()) != null) {
                CsvRecord record = getCsvRecord(delimiter, line, headers);
                csvRecordRepository.save(record);
                csvUploadCounter.increment();
            }
        } catch (IOException e) {
            log.error("Error occurred when trying to parse csv file! File: " + '\n' + file + "|e = " + e);
        }
    }

    private static CsvRecord getCsvRecord(String delimiter, String line, String[] headers) {
        String[] data = line.split(delimiter);
        Map<String, String> dataMap = new HashMap<>();
        if (headers != null) {
            for (int i = 0; i < data.length; i++) {
                dataMap.put(headers[i], data[i]);
            }
        } else {
            for (int i = 0; i < data.length; i++) {
                dataMap.put("column" + (i + 1), data[i]);
            }
        }
        CsvRecord record = new CsvRecord();
        record.setData(dataMap);
        return record;
    }

    public List<CsvRecord> getAllRecords() {
        return csvRecordRepository.findAll();
    }

    public List<CsvRecord> searchCsvData(String keyword) {
        return csvRecordRepository.search(keyword);
    }
}
