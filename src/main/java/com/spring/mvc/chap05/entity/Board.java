package com.spring.mvc.chap05.entity;

import com.spring.mvc.chap05.dto.BoardRequestDTO;
import lombok.*;

import java.time.LocalDateTime;

/*
    create table tbl_board (
    board_no int(10) auto_increment primary key,
    title varchar(80) not null,
    content varchar(2000),
    view_count int(10) default 0,
    reg_date_time Date default current_timestamp
    );
*/

@Setter @Getter
@ToString @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {
    private int boardNo; // 글번호
    private String title; // 제목
    private String content; // 내용
    private int viewCount; // 조회수
    private LocalDateTime regDateTime; // 작성일자 시간

    public Board(BoardRequestDTO dto) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.viewCount = 0;
        this.regDateTime = LocalDateTime.now();
    }
}
