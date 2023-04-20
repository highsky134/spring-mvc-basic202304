package com.spring.mvc.chap04.entity;

import com.spring.mvc.chap04.dto.ScoreRequestDTO;
import lombok.*;

@Setter @Getter
@ToString @EqualsAndHashCode
@NoArgsConstructor @AllArgsConstructor
public class Score {

    private String name; // 학생 이름
    private int kor, eng, math; // 국, 영, 수 점수

    private int stuNum; // 학번
    private int total; // 총점
    private double avg; // 평균
    private Grade grade; //학점

    public Score(ScoreRequestDTO dto) {
        this.name = dto.getName();
        this.kor = dto.getKor();
        this.eng = dto.getEng();
        this.math = dto.getMath();
        calcTotalAndAvg(); // 총점, 평균 계싼
        calcGrade(); // 학점 계산
    }

    private void calcGrade() {
        if (avg >= 90) {
            this.grade = Grade.A;
        } else if (avg >= 80) {
            this.grade = Grade.B;
        } else if (avg >= 70) {
            this.grade = Grade.C;
        } else if (avg >= 60) {
            this.grade = Grade.D;
        } else {
            this.grade = Grade.F;
        }
    }

    private void calcTotalAndAvg() {
        this.total = kor + eng + math;
        this.avg = Math.round((total / 3.0) * 100) / 100.0;
    }

    public void modifyScore() {
        calcTotalAndAvg();
        calcGrade();
    }
}
