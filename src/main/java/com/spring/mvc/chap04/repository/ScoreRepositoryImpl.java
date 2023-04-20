package com.spring.mvc.chap04.repository;

import com.spring.mvc.chap04.dto.ScoreRequestDTO;
import com.spring.mvc.chap04.dto.ScoreUpdateDTO;
import com.spring.mvc.chap04.entity.Grade;
import com.spring.mvc.chap04.entity.Score;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

import static com.spring.mvc.chap04.entity.Grade.*;
import static java.util.Comparator.*;

@Repository // 스프링 빈 등록 : 객체의 생성의 제어권을 스프링에게 위임(IoC)
public class ScoreRepositoryImpl implements ScoreRepository{

    // key : 학번, value : 성적정보Score
    private static final Map<Integer, Score> scoreMap;

    // 학번에 사용할 일련번호
    private static int sequence;

    static {
        scoreMap = new HashMap<>();
        Score stu1 = new Score(new ScoreRequestDTO("뽀로로", 100, 50, 70));
        Score stu2 = new Score(new ScoreRequestDTO("춘식이", 33, 56, 12));
        Score stu3 = new Score(new ScoreRequestDTO("대길이", 88, 12, 0));

        stu1.setStuNum(++sequence);
        stu2.setStuNum(++sequence);
        stu3.setStuNum(++sequence);

        scoreMap.put(stu1.getStuNum(), stu1);
        scoreMap.put(stu2.getStuNum(), stu2);
        scoreMap.put(stu3.getStuNum(), stu3);
    }

//    public List<Score> findAll() {
////        if (sortType == 1) { // 학번
//        return new ArrayList<>(scoreMap.values())
//                .stream()
//                .sorted(comparing(Score::getStuNum))
//                .collect(Collectors.toList());
//    }

    @Override
    public List<Score> findAll(String sort) {
        Comparator<Score> comparator = comparing(Score::getStuNum);
        switch (sort) {
            case "num":
                comparator = comparing(Score::getStuNum);
                break;
            case "name":
                comparator = comparing(Score::getName);
                break;
            case "avg":
                comparator = comparing(Score::getAvg).reversed();
                break;
        }

        return new ArrayList<>(scoreMap.values())
                    .stream()
                    .sorted(comparator)
                    .collect(Collectors.toList());
    }

    @Override
    public boolean save(Score score) {
        if ( scoreMap.containsKey(score.getStuNum())) {
            return false;
        }
        score.setStuNum(++sequence);
        scoreMap.put(score.getStuNum(), score);
//        System.out.println(findAll());
        return true;
    }

    @Override
    public boolean deleteByStuNum(int stuNum) {
        if (!scoreMap.containsKey(stuNum)) return false;
        scoreMap.remove(stuNum);
        return true;
    }

    @Override
    public Score findByStuNum(int stuNum) {
        return scoreMap.get(stuNum);
    }

    @Override
    public Score modify(ScoreUpdateDTO dto) {
        Score score = findByStuNum(dto.getStuNum());
        score.setKor(dto.getKor());
        score.setEng(dto.getEng());
        score.setMath(dto.getMath());
        score.modifyScore();
        return score;
    }


}
