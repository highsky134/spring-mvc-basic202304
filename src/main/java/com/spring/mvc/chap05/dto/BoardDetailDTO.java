package com.spring.mvc.chap05.dto;

import com.spring.mvc.chap05.entity.Board;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class BoardDetailDTO {
    private int boardNo; // 글번호
    private String title; // 제목
    private String content; // 내용
    private int viewCount; // 조회수
    private String regDateTime; // 작성일자 시간


    public BoardDetailDTO(Board board) {
        this.boardNo = board.getBoardNo();
        this.title = board.getTitle();
        this.content = board.getContent();
        board.setViewCount(board.getViewCount()+1);
        this.viewCount = board.getViewCount();
        this.regDateTime = strDateTime(board.getRegDateTime());
    }

    private String strDateTime(LocalDateTime regDateTime) {
        return regDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
