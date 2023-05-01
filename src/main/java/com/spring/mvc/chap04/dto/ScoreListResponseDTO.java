package com.spring.mvc.chap04.dto;

import com.spring.mvc.chap04.entity.Grade;
import com.spring.mvc.chap04.entity.Score;
import lombok.*;

@RequiredArgsConstructor // final만 골라서 초기화
@Getter @ToString @EqualsAndHashCode
public class ScoreListResponseDTO {
    private final int stuNum;
    private final String maskingName; // 첫글자 빼고 *처리
    private final double avg;
    private final Grade grade;


    public ScoreListResponseDTO(Score s) {
        this.stuNum = s.getStuNum();
        this.maskingName = makeMaskingName(s.getName());
        this.avg = s.getAverage();
        this.grade = s.getGrade();
    }

    // 첫글자만 빼고 * 처리
    private String makeMaskingName(String originalName) {
        String maskingName = String.valueOf(originalName.charAt(0));
        for (int i = 1; i < originalName.length(); i++) {
            maskingName += "*";
        }
        return maskingName;
    }
}