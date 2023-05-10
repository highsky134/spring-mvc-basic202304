package com.spring.mvc.chap05.service;

import com.spring.mvc.chap05.dto.LoginRequestDTO;
import com.spring.mvc.chap05.dto.SignupRequestDTO;
import com.spring.mvc.chap05.entity.Member;
import com.spring.mvc.chap05.repository.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.spring.mvc.chap05.service.LoginResult.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;
    private final PasswordEncoder encoder;

    // 회원가입 처리 서비스
    public void join(SignupRequestDTO dto) {


        // dto를 entity로 변환
        Member member = Member.builder()
                .account(dto.getAccount())
                .password(encoder.encode(dto.getPassword()))
                .name(dto.getName())
                .email(dto.getEmail())
                .build();

        memberMapper.save(member);
    }

    // 중복검사 서비스 처리
    public boolean checkSignUpValue(String type, String keyword) {

        int flagNum = memberMapper.isDuplicate(type, keyword);
        return flagNum == 1;
    }

    public LoginResult authenticate(LoginRequestDTO dto) {

        Member foundMember = memberMapper.findMember(dto.getAccount());

        if (foundMember == null) {
            log.info("{} - 회원가입 안했음ㅋ", dto.getAccount());
            return NO_ACC;
        }
        // 비밀번호 일치 확인
        if (!encoder.matches(dto.getPassword(), foundMember.getPassword())) {
            log.info("비밀번호 불일치!");
            return NO_PW;
        }

        log.info("{}님 로그인 성공!", foundMember.getName());
        return SUCCESS;

    }

    // 로그인
}
