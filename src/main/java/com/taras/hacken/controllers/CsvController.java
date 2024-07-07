package com.taras.hacken.controllers;

import com.taras.hacken.services.csvService.CsvService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/csv")
public class CsvController {

    private CsvService csvService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadCsv(@RequestParam("file") MultipartFile file,
                                            @RequestParam("hasHeader") boolean hasHeader,
                                            @RequestParam(name = "delimiter", defaultValue = ",") String delimiter) {
        csvService.uploadCsv(file, hasHeader, delimiter);
        return ResponseEntity.status(HttpStatus.OK).body("File uploaded successfully;");
    }

}
