package com.spring.mvc.chap05.service;

import com.spring.mvc.chap05.dto.LoginRequestDTO;
import com.spring.mvc.chap05.dto.SignupRequestDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {


    @Autowired
    MemberService memberService;

    @Test
    @DisplayName("dto를 주면 회원가입에 성공해야 한다")
    void joinTest() {
        SignupRequestDTO dto = new SignupRequestDTO();
        dto.setAccount("김씨33");
        dto.setName("홍길동23");
        dto.setPassword("12313");
        dto.setEmail("baaa@naver.com");

        memberService.join(dto);

    }

    @Test
    @DisplayName("계정명이 aaaa인 회원의 로그인시도 결과 검증을 상황별로 수행해야 한다")
    void loginTest() {
        // given
        String account = "aaaa";
        LoginRequestDTO dto = new LoginRequestDTO();
        dto.setAccount("aaaa");
        dto.setPassword("1234!");

        // when
        LoginResult result = memberService.authenticate(dto);

        // then
        assertEquals(LoginResult.SUCCESS, result);

    }

}