package com.spring.mvc.chap05.dto;

import lombok.*;

import java.time.LocalDateTime;

@Setter @Getter
@ToString @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class BoardRequestDTO {
    private String title; // 제목
    private String content; // 내용
    private String account; // 작성자

}
