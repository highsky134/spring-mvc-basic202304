package com.spring.mvc.chap04.repository;

import com.spring.mvc.chap04.dto.ScoreRequestDTO;
import com.spring.mvc.chap04.entity.Score;
import com.spring.mvc.chap04.service.ScoreService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScoreRepositoryImplTest {

    ScoreRepository repository = new ScoreRepositoryImpl();
    ScoreService scoreService = new ScoreService(repository);

    // 단위 테스트 (Unit test)
    // 테스트 시나리오
    // - 단언(Assertion) 기법
    @Test
    @DisplayName("저장소에서 findAll을 호출하면 반환된 리스트에는 성적정보가 3개 있어야 한다.")
    void findAllTest() {
        // given-when-then 패턴
        // given: 테스트를 위해 주어질 데이터 (ex: parameter)

        // when: 테스트 실제 상황
        List<Score> scoreList = repository.findAll("name");

        // then: 테스트 결과 확인
        System.out.println(scoreList.size() == 3);

        // 나는 스코어리스트의 사이즈가 3인 것이 참이라고 단언한다.
//        assertTrue(scoreList.size() == 3);
        assertEquals(3, scoreList.size());

        // 나는 리스트의 첫번째 객체의 이름이 뽀로로라고 단언한다.
        assertEquals("뽀로로",scoreList.get(0).getName());

    }

    @Test
    @DisplayName("저장소에서 findByStuNum을 호출하여 학번이 2인 학생을 조회하면 " +
            "그 학생의 국어점수가 33점이고 이름이 춘식이어야 한다")
    void findOneTest() {

        // given
        int stuNum = 2;
        // when
        Score score = repository.findByStuNum(stuNum);
        // then
        assertEquals(33,score.getKor());
        assertEquals("춘식이", score.getName());
    }

    @Test
    @DisplayName("저장소에서 findByStuNum을 호출하여 학번이 99인 학생을 조회하면 " +
            "null이 리턴될 것이다.")
    void findOneTest2() {

        // given
        int stuNum = 99;
        // when
        Score score = repository.findByStuNum(stuNum);
        // then
        assertNull(score);
    }

    @Test
    @DisplayName("저장소에서 학번이 2인 학생을 삭제한 후에" +
            "리스트를 전체조회해보면 성적의 개수가 2개일 것이고" +
            "다시 2번 학생을 조회했을때 null이 반환되어야 한다.")
    void deleteTest() {
        // given
        int stuNum = 2;
        // when
        repository.deleteByStuNum(stuNum);
        List<Score> scoreList = repository.findAll("name");
        Score score = repository.findByStuNum(stuNum);
        // then
        assertEquals(2,scoreList.size());
        assertNull(score);

        scoreList.forEach(s -> System.out.println(s));
    }

    @Test
    @DisplayName("새로운 성적정보를 save를 통해 추가하면" +
            "모든 데이터가 잘 등록 되어야 한다.")
    void saveTest() {
        ScoreRequestDTO score = new ScoreRequestDTO();
        score.setName("언년이");
        score.setKor(71);
        score.setEng(55);
        score.setMath(42);

        boolean flag = scoreService.insertScore(score);
//        List<Score> scoreList = repository.findAll("name");

//        assertEquals(4, scoreList.size());
        assertTrue(flag);
//        assertEquals(4, scoreList.get(scoreList.size() - 1).getStuNum());
    }
}