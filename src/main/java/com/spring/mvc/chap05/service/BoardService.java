package com.spring.mvc.chap05.service;

import com.spring.mvc.chap05.dto.BoardDetailDTO;
import com.spring.mvc.chap05.dto.BoardListResponseDTO;
import com.spring.mvc.chap05.dto.BoardRequestDTO;
import com.spring.mvc.chap05.dto.page.Page;
import com.spring.mvc.chap05.entity.Board;
import com.spring.mvc.chap05.repository.BoardMapper;
import com.spring.mvc.chap05.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

//    private final BoardRepository boardRepository;
    private final BoardMapper boardRepository;

    // 보드 전체 보여주기 중간처리
    public List<BoardListResponseDTO> getList(Page page) {


        return boardRepository.findAll(page)
                .stream()
                .map(BoardListResponseDTO::new)
                .collect(Collectors.toList());
    }

    // 게시글 등록하기
    public boolean write(BoardRequestDTO dto){
        return boardRepository.save(new Board(dto));
    }

    // 게시글 삭제하기
    public boolean delete(int boardNo) {
        return boardRepository.deleteByNo(boardNo);
    }

    // 게시글 상세조회
    public BoardDetailDTO findOne(int boardNo) {
        Board board = boardRepository.findOne(boardNo);
        boardRepository.upViewCount(boardNo);
        return new BoardDetailDTO(board);
    }

    public int getCount() {
        return boardRepository.count();
    }

    // 수정하기
//    public boolean modify() {
//
//    }
    // 중간 처리 기능
}
