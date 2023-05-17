package com.spring.mvc.chap05.service;

import com.spring.mvc.chap05.dto.ReplyListResponseDTO;
import com.spring.mvc.chap05.dto.ReplyPostRequestDTO;
import com.spring.mvc.chap05.dto.ReplyPutRequestDTO;
import com.spring.mvc.chap05.dto.page.Page;
import com.spring.mvc.chap05.dto.page.PageMaker;
import com.spring.mvc.chap05.dto.ReplyDetailResponseDTO;
import com.spring.mvc.chap05.dto.response.LoginUserResponseDTO;
import com.spring.mvc.chap05.entity.Reply;
import com.spring.mvc.chap05.repository.ReplyMapper;
import com.spring.mvc.util.LoginUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReplyService {

    private final ReplyMapper replyMapper;
    private int count;

    // 댓글 목록 조회 서비스
    public ReplyListResponseDTO getReplyList(long boardNo, Page page ) {

        List<ReplyDetailResponseDTO> replies = replyMapper.findAll(boardNo, page).stream()
                .map(ReplyDetailResponseDTO::new)
                .collect(Collectors.toList());

        int count = replyMapper.count(boardNo);
        return ReplyListResponseDTO.builder()
                .count(count)
                .pageInfo(new PageMaker(page, count))
                .replies(replies)
                .build();
    }

    // 댓글 등록 서비스
    public ReplyListResponseDTO register(
            final ReplyPostRequestDTO dto,
            HttpSession session
    ) throws SQLException {
        log.debug("register service execute!!");
        // dto를 entity로 변환
        Reply reply = dto.toEntity();
        LoginUserResponseDTO member = (LoginUserResponseDTO) session.getAttribute(LoginUtil.LOGIN_KEY);
        reply.setAccount(member.getAccount());
        reply.setReplyWriter(member.getNickName());

        boolean flag = replyMapper.save(reply);
        if (!flag) {
            log.warn("reply registered fail!");
            throw new SQLException("댓글 저장 실패");
        }
        return getReplyList(dto.getBno(), new Page(1, 10));
    }

    // 댓글 삭제 서비스
    @Transactional
    public ReplyListResponseDTO delete(final long replyNo) throws Exception {

        long boardNo = replyMapper.findOne(replyNo).getBoardNo();
        replyMapper.deleteOne(replyNo);

        return getReplyList(boardNo, new Page(1,10));
    }


    public ReplyListResponseDTO modify(ReplyPutRequestDTO dto) throws Exception {
//        Reply r = replyMapper.findOne(dto.getReplyNo());

        replyMapper.modify(dto.toEntity());
        return getReplyList(dto.getBoardNo(), new Page(1,10));

    }

}
