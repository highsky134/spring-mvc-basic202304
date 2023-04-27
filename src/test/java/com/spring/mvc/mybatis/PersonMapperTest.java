package com.spring.mvc.mybatis;

import com.spring.mvc.jdbc.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonMapperTest {

    @Autowired
    PersonMapper mapper;

    @Test
    @DisplayName("마이바티스 매퍼로 사람정보 저장에 성공해야 한다.")
    void saveTest() {
        Person p = Person.builder()
                .personName("정동관")
                .personAge(50)
                .build();

        boolean flag = mapper.save(p);
        assertTrue(flag);
    }

    @Test
    @DisplayName(" 수정에 성공해야 한다")
    void changeTest() {
        Person p = Person.builder()
                .personName("췌인지")
                .personAge(20)
                .id(3L)
                .build();
        boolean flag = mapper.change(p);
        assertTrue(flag);
    }

    @Test
    @DisplayName("삭제에 성공해야 한다")
    void removeTest() {
        long id = 5L;
        boolean delete = mapper.delete(id);
        assertTrue(delete);
    }

    @Test
    @DisplayName("전체 정보 조회에 성공해야한다!")
    void findAllTest() {
        List<Person> people = mapper.findAll();
        people.forEach(System.out::println);

        assertEquals(3,people.size());
    }

    @Test
    void findOneTest() {
        long id = 2L;

        Person p = mapper.findOne(id);

        System.out.println("p = " + p);
    }

}