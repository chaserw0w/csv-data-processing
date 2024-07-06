package com.taras.hacken.controllers;

import com.taras.hacken.domain.Person;
import com.taras.hacken.services.CsvService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CsvController {

    private CsvService csvService;

    @PostMapping("/upload-csv")
    public ResponseEntity<String> uploadCsv(@RequestParam("file") MultipartFile file,
                                            @RequestParam("hasHeader") boolean hasHeader,
                                            @RequestParam(name = "delimiter", defaultValue = ",") String delimiter) {
        csvService.uploadCsv(file, hasHeader, delimiter);
        return ResponseEntity.status(HttpStatus.OK).body("File uploaded successfully;");
    }

    @GetMapping("/search")
    public ResponseEntity<List<Person>> searchByName(@RequestParam("name") String name) {
        List<Person> personList = csvService.searchByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(personList);
    }
}
