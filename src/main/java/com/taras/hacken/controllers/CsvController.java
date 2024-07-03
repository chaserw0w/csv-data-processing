package com.taras.hacken.controllers;

import com.taras.hacken.services.CsvService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class CsvController {

    private CsvService csvService;

    @PostMapping("/upload-csv")
    public ResponseEntity<String> uploadCsv(@RequestParam("file") MultipartFile file) {
        csvService.uploadCsv(file);
        return ResponseEntity.status(HttpStatus.OK).body("File uploaded successfully;");
    }
}
