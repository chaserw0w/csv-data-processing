package com.taras.hacken.services;

import com.taras.hacken.domain.Person;
import com.taras.hacken.domain.enums.GenderType;
import com.taras.hacken.repo.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CsvService {

    private PersonRepository personRepository;

    public void uploadCsv(MultipartFile file) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            List<Person> personList = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                Person person = new Person(null, data[0], data[1], GenderType.valueOf(data[2]), data[3], data[4], data[5]);     //todo: might produce errors
                personList.add(person);
            }
            personRepository.saveAll(personList);
        } catch (IOException e) {
            log.error("Error occurred when trying to upload csv!"); //todo: more details about the error
        }
    }
}
