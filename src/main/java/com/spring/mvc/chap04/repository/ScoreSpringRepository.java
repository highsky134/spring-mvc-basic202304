package com.spring.mvc.chap04.repository;

import com.spring.mvc.chap04.dto.ScoreUpdateDTO;
import com.spring.mvc.chap04.entity.Score;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("spring")
@RequiredArgsConstructor
public class ScoreSpringRepository implements ScoreRepository {

    private final JdbcTemplate jdbcTemplate;


    @Override
    public List<Score> findAll() {
        return null;
    }

    @Override
    public List<Score> findAll(String sort) {
        String sql = "select * from scores";
        return jdbcTemplate.query(sql, (rs, n) -> new Score(rs));
    }

    @Override
    public boolean save(Score score) {
        String sql = "insert into scores (name, kor, eng, math, total, average, grade) " +
                "values (?, ?, ?, ?, ?, ? ,?)";
        int result = jdbcTemplate.update(sql, score.getName(), score.getKor(), score.getEng(), score.getMath(),
                score.getTotal(), score.getAvg(), score.getGrade().name());
        return result == 1;
    }

    @Override
    public boolean deleteByStuNum(int stuNum) {
        String sql = "delete from scores where stu_num=?";
        int result = jdbcTemplate.update(sql, stuNum);
        return result == 1;
    }

    @Override
    public Score findByStuNum(int stuNum) {
        String sql = "select * from scores where stu_num=?";
        return jdbcTemplate.queryForObject(sql, (rs, n) -> new Score(rs), stuNum);
    }

    @Override
    public Score modify(ScoreUpdateDTO dto) {
//        String sql = "update scores set "
        return null;
    }
}
