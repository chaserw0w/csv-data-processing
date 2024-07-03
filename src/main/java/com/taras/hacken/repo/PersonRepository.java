package com.taras.hacken.repo;

import com.taras.hacken.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByNameContaining(String name);
}
