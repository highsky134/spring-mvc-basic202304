package com.spring.mvc.chap05.dto;

import com.spring.mvc.chap05.entity.Board;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Getter @ToString @EqualsAndHashCode
public class BoardListResponseDTO {

    private final int boardNo; // 글번호
    private final String reduceTitle; // 제목
    private final String reduceContent; // 내용
    private final int viewCount; // 조회수
    private final String regDateTime; // 작성일자 시간
    private final String account;

    public BoardListResponseDTO(Board b){
        this.boardNo = b.getBoardNo();
        this.reduceTitle = reduceTitle(b.getTitle()); // 제목 7글자
        this.reduceContent = reduceContent(b.getContent());
        this.viewCount = b.getViewCount();
        this.regDateTime = strDateTime(b.getRegDateTime());
        this.account = b.getAccount();
    }

//    public BoardListResponseDTO(int boardNo,Board b){
//        this.boardNo = boardNo;
//        this.reduceTitle = b.getTitle(); // 제목 7글자
//        this.reduceContent = b.getContent();
//        this.viewCount = b.getViewCount();
//        this.regDateTime = strDateTime(b.getRegDateTime());
//    }

    private String strDateTime(LocalDateTime regDateTime) {
        return regDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    private String reduceContent(String content) {
        return shortString(content, 30);
    }

    // 제목 7글자 제한
    private String reduceTitle(String title) {
        return shortString(title, 7);
    }

    private String shortString(String target, int wishLength) {
        return (target.length() > wishLength)
                ? target.substring(0, wishLength) + "..."
                : target;
    }
}
