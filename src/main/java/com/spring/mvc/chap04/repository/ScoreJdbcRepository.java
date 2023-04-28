package com.spring.mvc.chap04.repository;

import com.spring.mvc.chap04.dto.ScoreUpdateDTO;
import com.spring.mvc.chap04.entity.Score;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository("jdbc")
public class ScoreJdbcRepository implements ScoreRepository{

    private String url = "jdbc:mariadb://localhost:3306/spring";
    private String username = "root";
    private String password = "1234";

    public ScoreJdbcRepository() {
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
            pstmt.setDouble(6,score.getAverage());
            pstmt.setString(7, score.getGrade().name());
            int result = pstmt.executeUpdate();

            if (result == 1) {
                conn.commit();
                return true;
            }
            conn.rollback();
            return false;
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return false;
    }

//    @Override
//    public List<ScoreListResponseDTO> findAll(String sort) {
//        try (Connection conn = DriverManager.getConnection(url, username, password)) {
//
//            List<ScoreListResponseDTO> list = new ArrayList<>();
//
//            String sql = "SELECT * FROM scores";
//            if (sort.equals("num")) sql += " order by stu_num";
//            else if (sort.equals("name")) sql += " order by name";
//            else sql += " order by average desc";
//
//
//            PreparedStatement pstmt = conn.prepareStatement(sql);
//            pstmt.setString(1, sort);
//
//            ResultSet rs = pstmt.executeQuery();
//
//            while (rs.next()) {
//                // 생성자로 객체를 만들어서 그걸 리턴 해주는게 맞을까?
//                // 그냥 Score를 사용하는게 맞을까?
//
//                String name = rs.getString("name");
//                int kor = rs.getInt("kor");
//                int eng = rs.getInt("eng");
//                int math = rs.getInt("math");
//                ScoreRequestDTO requestDTO = new ScoreRequestDTO(name, kor, eng, math);
//
//                Score s = new Score(requestDTO);
//                s.setStuNum(rs.getInt("stu_num"));
//
//                ScoreListResponseDTO dto = new ScoreListResponseDTO(s);
//                System.out.println("dto = " + dto);
//                list.add(dto);
//            }
//            return list;
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    @Override
    public List<Score> findAll() {
        return null;
    }

    public List<Score> findAll(String sort) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {

            List<Score> list = new ArrayList<>();

            String sql = "SELECT * FROM scores";
//            if (sort.equals("num")) sql += " order by stu_num";
//            else if (sort.equals("name")) sql += " order by name";
//            else sql += " order by average desc";


            PreparedStatement pstmt = conn.prepareStatement(sql);
//            pstmt.setString(1, sort);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                list.add(new Score(rs));
            }
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public boolean deleteByStuNum(int stuNum) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {

            String sql = "delete from scores where stu_num = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, stuNum);
            int result = pstmt.executeUpdate();

            if (result == 1) {
                conn.commit();
                return true;
            }
            conn.rollback();
            return false;


        } catch (SQLException e) {

            throw new RuntimeException(e);
        }

    }

    @Override
    public Score findByStuNum(int stuNum) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {

              String sql = "SELECT * FROM scores where stu_num=?";


            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, stuNum);


            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) return new Score(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int modify(ScoreUpdateDTO dto) {
        return 0;
    }
}
