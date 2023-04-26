package com.spring.mvc.spring_jdbc;

import com.spring.mvc.jdbc.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonSpringRepositoryTest {

    @Autowired
    PersonSpringRepository repository;

    @Test
    void savePersonTest() {
        // given
        Person p = new Person();
        p.setPersonName("스프링킴");
        p.setPersonAge(22);
        // when
        repository.savePerson(p);
    }

    @Test
    void removePersonTest() {
        // given
        long id = 1L;
        // when
        repository.removePerson(id);
    }

    @Test
    void updatePersonTest() {
        String newName = "조경훈";
        int newAge = 35;
        long id = 3L;

        repository.updatePerson(newName, newAge, id);
    }

    @Test
    void findAllTest() {
        List<Person> people = repository.findAll();

        for (Person p : people) {
            System.out.println("p = " + p);
        }
    }

    @Test
    void findOneTest() {
        long id = 2L;

        Person p = repository.findOne(id);
        System.out.println("p = " + p);

        assertEquals("대길이", p.getPersonName());
    }
}