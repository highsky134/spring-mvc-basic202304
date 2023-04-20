package com.spring.mvc.chap04.dto;

import lombok.*;

@Setter @Getter
@ToString @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ScoreRequestDTO {

    private String name; // 학생 이름
    private int kor, eng, math; // 국, 영, 수 점수
    // 변수명은 입력받는 파라미터의 name과 일치하게 만든거임
}
