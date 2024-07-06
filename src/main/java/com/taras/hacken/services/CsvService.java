package com.taras.hacken.services;

import com.taras.hacken.domain.Person;
import com.taras.hacken.domain.enums.GenderType;
import com.taras.hacken.repo.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CsvService {

    private PersonRepository personRepository;

    public void uploadCsv(MultipartFile file, boolean hasHeader, String delimiter) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            List<Person> personList = new ArrayList<>();
            if (hasHeader) {
                reader.readLine();
            }
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(delimiter);
                Person person = new Person(
                        Long.parseLong(data[0]),
                        data[1],
                        data[2],
                        GenderType.valueOf(data[3]),
                        data[4],
                        LocalDate.parse(data[5], format),
                        data[6]
                );
                personList.add(person);
            }
            personRepository.saveAll(personList);
        } catch (IOException e) {
            log.error("Error occurred when trying to upload csv!" + "\n" + file + "|" + e);
        }
    }

    public List<Person> searchByName(String name) {
        return personRepository.findByNameContaining(name);
    }
}
