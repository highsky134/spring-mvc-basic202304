package com.spring.mvc.chap04.dto;

import lombok.*;

@Setter @Getter
@ToString @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ScoreUpdateDTO {
    private int kor, eng, math; // 국, 영, 수 점수
    private int stuNum; // 학번
}
