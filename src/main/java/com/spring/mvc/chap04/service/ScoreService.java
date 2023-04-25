package com.spring.mvc.chap04.service;

import com.spring.mvc.chap04.dto.ScoreListResponseDTO;
import com.spring.mvc.chap04.dto.ScoreRequestDTO;
import com.spring.mvc.chap04.dto.ScoreUpdateDTO;
import com.spring.mvc.chap04.entity.Score;
import com.spring.mvc.chap04.repository.ScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// 컨트롤러와 레파지토리 사이 비즈니스 로직 처리
// ex) 트랜잭션 처리, 예외처리, dto변환 처리
@Service
@RequiredArgsConstructor
public class ScoreService {

    private final ScoreRepository scoreRepository;

    // 목록조회 중간처리
    /*
        컨트롤러는 데이터베이스를 통해
        성적정보 리스트를 가져오길 원한다.
        그런데 데이터베이스는 성적정보를 전부 모아서 준다
        컨트롤러는 일부만 받았으면 좋겠다.
     */
    public List<ScoreListResponseDTO> getList(String sort) {

        String type;
        switch (sort) {
            case "avg":
                type = "average";
                break;
            case "name":
                type = "name";
                break;
            default:
                type = "stu_num";
        }

        return scoreRepository.findAll(type);
//                .stream()
//                .map(ScoreListResponseDTO::new)
//                .collect(Collectors.toList());

        // scoreList에서 원하는 정보만 추출하고 이름은 마스킹해서
        // 다시 DTO리스트로 변환해줘야 한다.
//        return scoreRepository.findAll(sort).stream()
//                .map(ScoreListResponseDTO::new)
//                .collect(Collectors.toList());
    }

    // 등록 중간처리
    public boolean insertScore(ScoreRequestDTO dto) {
        // dto(ScoreDTO)를 entity(Score)로 변환해야함
        // save 명령
        return scoreRepository.save(new Score(dto));
    }

    // 삭제 중간처리
    public boolean delete(int stuNum) {
        return scoreRepository.deleteByStuNum(stuNum);
    }

    // 상세조회, 수정화면조회 중간처리
    public Score retrieve(int stuNum) {
        return scoreRepository.findByStuNum(stuNum);
    }

    public void modify(ScoreUpdateDTO dto) {
        scoreRepository.modify(dto);
    }
}
