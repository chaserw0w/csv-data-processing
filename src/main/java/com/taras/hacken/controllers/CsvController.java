package com.taras.hacken.controllers;

import com.taras.hacken.domain.CsvRecord;
import com.taras.hacken.services.csvService.CsvService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/csv")
public class CsvController {

    private final CsvService csvService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadCsv(@RequestParam("file") MultipartFile file,
                                            @RequestParam("hasHeader") boolean hasHeader,
                                            @RequestParam(name = "delimiter", defaultValue = ",") String delimiter) {
        csvService.uploadCsv(file, hasHeader, delimiter);
        return ResponseEntity.status(HttpStatus.OK).body("File uploaded successfully;");
    }

    @GetMapping("/records")
    public ResponseEntity<List<CsvRecord>> getAllRecords() {
        List<CsvRecord> records = csvService.getAllRecords();
        return ResponseEntity.ok(records);
    }

    @GetMapping("/search")
    public ResponseEntity<List<CsvRecord>> searchCsvData(@RequestParam String keyword) {
        List<CsvRecord> results = csvService.searchCsvData(keyword);
        return ResponseEntity.ok(results);
    }

}
