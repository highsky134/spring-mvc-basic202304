//package com.spring.mvc.chap05.repository;
//
//import com.spring.mvc.chap05.entity.Board;
//import org.springframework.stereotype.Repository;
//
//import java.time.LocalDateTime;
//import java.util.*;
//import java.util.stream.Collectors;
//
//import static java.util.Comparator.*;
//import static java.util.stream.Collectors.*;
//
//@Repository
//public class BoardRepositoryImpl implements BoardRepository {
//
//    private final static Map<Integer, Board> boardMap;
//    private static int sequence;
//    static {
//        boardMap = new HashMap<>();
//
//        Board b1 = new Board(++sequence, "첫번째 게시글", "일단 아무거나 가나다라마바사아자차카타파하 123456789 오늘 점심은 뭐먹지? 점메추", 0, LocalDateTime.now());
//        Board b2 = new Board(++sequence, "두번째 게시글", "일단 아무거나", 0, LocalDateTime.now());
//
//        boardMap.put(b1.getBoardNo(), b1);
//        boardMap.put(b2.getBoardNo(), b2);
//
//    }
//
//    @Override
//    public List<Board> findAll() {
//
//        return boardMap.values()
//                .stream()
//                .sorted(comparing(Board::getBoardNo).reversed())
//                .collect(toList());
//    }
//
//    @Override
//    public Board findOne(int boardNo) {
//        return boardMap.get(boardNo);
//    }
//
//    @Override
//    public boolean save(Board board) {
//
//        board.setBoardNo(++sequence);
//        boardMap.put(board.getBoardNo(),board);
//
//        return true;
//    }
//
//    @Override
//    public boolean deleteByNo(int boardNo) {
//        boardMap.remove(boardNo);
//        return true;
//    }
//}
