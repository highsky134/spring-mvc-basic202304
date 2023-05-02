package com.spring.mvc.chap05.repository;

import com.spring.mvc.chap05.dto.page.Search;
import com.spring.mvc.chap05.entity.Board;
import com.spring.mvc.chap05.entity.Reply;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReplyMapperTest {

    @Autowired
    BoardMapper boardMapper;
    @Autowired
    ReplyMapper replyMapper;

//    @Test
//    @DisplayName("게시물 300개를 등록하고 각 게시물에 랜덤으로 1000개의 댓글을 나눠서 등록해야 한다.")
//    void bulkInsertTest() {
//        for (int i = 1; i <= 300; i++) {
//            Board b = Board.builder()
//                    .title("재밌는 게시물 " + i)
//                    .content("유잼 게시물 내용 " + i)
//                    .build();
//            boardMapper.save(b);
//        }
//        assertEquals(300, boardMapper.count(new Search()));
//
//        for (int i = 1; i <= 1000; i++) {
//            Reply r = Reply.builder()
//                    .replyWriter("잼민이 " + i)
//                    .replyText("말똥이 ~~~" + i)
//                    .boardNo((long) (Math.random() * 300 + 1))
//                    .build();
//            replyMapper.save(r);
//        }
//    }

    @Test
    @DisplayName("댓글을 3번 게시물에 등록하면 3번 게시물의 총 댓글 수는 8개 여야 한다")
    @Transactional
    @Rollback // 테스트 끝난 이후 롤백해라
    void saveTest() {
        // given
        long boardNo = 3L;
        Reply newReply = Reply.builder()
                .replyText("세이브 세이브33")
                .replyWriter("새잼민이")
                .boardNo(boardNo)
                .build();
        // when
        boolean flag = replyMapper.save(newReply);

        // then
        assertTrue(flag); // 세이브가 성공했을것이라 단언
        assertEquals(8, replyMapper.count(boardNo));
//        assertEquals("새잼민이", replyMapper.findOne(boardNo).getReplyWriter());
    }

    @Test
    @DisplayName("댓글 번호가 1001번인 댓글을 삭제하면" +
            "3번 게시물의 총 댓글수가 6이어야 한다")
    @Transactional
    @Rollback
    void deleteTest() {
        long replyNo = 1001L;
        long boardNo = 3L;

        boolean flag = replyMapper.deleteOne(replyNo);

        assertTrue(flag);
        assertEquals(6, replyMapper.count(boardNo));

    }

    @Test
    @DisplayName("댓글 번호가 1001번 댓글의 내용을 수정하면 내용이 '수정수정2',  ")
    @Transactional
    @Rollback
    void modifyTest() {
        // given
        long replyNo = 1001L;
        String modi = "수정수정2";
        // when
        Reply r = Reply.builder()
                .replyText(modi)
                .replyNo(replyNo)
                .replyDate(LocalDateTime.now())
                .build();

        boolean flag = replyMapper.modify(r);
        int minute = r.getReplyDate().getMinute();

        // then
        assertTrue(flag);
        assertEquals(LocalDateTime.now().getMinute(),minute);
    }

    @Test
    @DisplayName("댓글 번호가 1002의 내용은 (세이브 세이브)어야 한다")
    void findOneTest() {
        long replyNo = 1002L;

        Reply r = replyMapper.findOne(replyNo);

        assertEquals("세이브 세이브", r.getReplyText());
    }
}