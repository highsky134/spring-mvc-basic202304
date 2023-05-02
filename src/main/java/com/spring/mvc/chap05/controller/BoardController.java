package com.spring.mvc.chap05.controller;

import com.spring.mvc.chap05.dto.BoardDetailDTO;
import com.spring.mvc.chap05.dto.BoardRequestDTO;
import com.spring.mvc.chap05.dto.page.Page;
import com.spring.mvc.chap05.dto.page.PageMaker;
import com.spring.mvc.chap05.dto.page.Search;
import com.spring.mvc.chap05.entity.Board;
import com.spring.mvc.chap05.entity.Reply;
import com.spring.mvc.chap05.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
@Slf4j
public class BoardController {

    private final BoardService boardService;

    // 게시물 리스트 조회
    @GetMapping("/list")
    public String list(Search page, Model model) {
        log.info("page : {}", page);

        // 페이징 알고리즘 작동
        PageMaker maker = new PageMaker(page, boardService.getCount(page));

        model.addAttribute("bList",boardService.getList(page));
        model.addAttribute("maker", maker);
        model.addAttribute("s",page);
        return "chap05/list";
    }

    // 게시물 상세조회
    @GetMapping("/detail")
    public String detail(@ModelAttribute("s") Search search, int boardNo, Model model) {
        System.out.println("/board/detail : GET!");
        BoardDetailDTO board = boardService.findOne(boardNo);
        List<Reply> replyList = boardService.getReplyList(boardNo, search);


//        String time = board.getRegDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
//        model.addAttribute("d", time);
        model.addAttribute("b", board);
        model.addAttribute("replyList",replyList);
//        model.addAttribute("s", search);
        return "chap05/detail";
    }

    @GetMapping("/write")
    public String write() {
        return "chap05/write";
    }

    // 게시물 등록하기
    @PostMapping("/insert")
    public String insert(BoardRequestDTO dto) {
        boardService.write(dto);
        return "redirect:/board/list";
    }

    // 게시물 삭제하기
    @GetMapping("/delete")
    public String delete(int boardNo) {
        System.out.println("boardNo = " + boardNo);
        boardService.delete(boardNo);

        return "redirect:/board/list";
    }

    @PostMapping("/reply")
    public String insertReply(Reply reply, Search search, Model model) {
//        , @ModelAttribute("s") Search search
        System.out.println("reply = " + reply);
        boardService.insertReply(reply);

        return String.format("redirect:/board/detail?bno=%d&pageNo=%d&type=%s&keyword=%s",
                reply.getBoardNo(),search.getPageNo(),search.getType(),search.getKeyword());
    }
}
