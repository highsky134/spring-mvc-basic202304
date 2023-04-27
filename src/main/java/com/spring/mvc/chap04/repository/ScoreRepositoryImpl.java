package com.spring.mvc.chap04.repository;

import com.spring.mvc.chap04.dto.ScoreListResponseDTO;
import com.spring.mvc.chap04.dto.ScoreRequestDTO;
import com.spring.mvc.chap04.dto.ScoreUpdateDTO;
import com.spring.mvc.chap04.entity.Grade;
import com.spring.mvc.chap04.entity.Score;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

import static com.spring.mvc.chap04.entity.Grade.*;
import static java.util.Comparator.*;

@Repository("memory") // 스프링 빈 등록 : 객체의 생성의 제어권을 스프링에게 위임(IoC)
public class ScoreRepositoryImpl implements ScoreRepository{

    private String url = "jdbc:mariadb://localhost:3306/spring";
    private String username = "root";
    private String password = "1234";

    public ScoreRepositoryImpl() {
        // 1. 드라이버 클래스를 로딩 (mariadb 커넥터 로딩)
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean save(Score score) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {

            conn.setAutoCommit(false);
            String sql = "INSERT INTO scores (name, kor, eng, math, total, average, grade) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, score.getName());
            pstmt.setInt(2,score.getKor());
            pstmt.setInt(3,score.getEng());
            pstmt.setInt(4,score.getMath());
            pstmt.setInt(5,score.getTotal());
            pstmt.setDouble(6,score.getAvg());
            pstmt.setString(7, score.getGrade().name());
            int result = pstmt.executeUpdate();

            if (result == 1) {
                conn.commit();
                return true;
            }
            else conn.rollback();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Score> findAll() {
        return null;
    }

    @Override
    public List<Score> findAll(String sort) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {

            List<Score> list = new ArrayList<>();

            String sql = "SELECT * FROM scores";
            if (sort.equals("num")) sql += " order by stu_num";
            else if (sort.equals("name")) sql += " order by name";
            else sql += " order by average desc";


            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, sort);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                // 생성자로 객체를 만들어서 그걸 리턴 해주는게 맞을까?
                // 그냥 Score를 사용하는게 맞을까?

                String name = rs.getString("name");
                int kor = rs.getInt("kor");
                int eng = rs.getInt("eng");
                int math = rs.getInt("math");
                ScoreRequestDTO requestDTO = new ScoreRequestDTO(name, kor, eng, math);

                Score s = new Score(requestDTO);
                s.setStuNum(rs.getInt("stu_num"));

                ScoreListResponseDTO dto = new ScoreListResponseDTO(s);
                System.out.println("dto = " + dto);
//                list.add(dto);
            }
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteByStuNum(int stuNum) {
        return false;
    }

    @Override
    public Score findByStuNum(int stuNum) {
        return null;
    }

    @Override
    public int modify(ScoreUpdateDTO dto) {
        return 0;
    }


    // 기존
    /*// key : 학번, value : 성적정보Score
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
    }*/


}
