package com.spring.mvc.chap01;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

// 어떤 요청들을 처리할지 공통 URL을 설계
@RequestMapping("/spring/*")
// 이 클래스의 객체를 스프링이 관리하도록 빈을 등록
@Controller // @Component와 같은 개념, 스프링이 더 찾기 쉽게함
public class ControllerV1 {

    // 세부 요청들은 메서드를 통해 처리
    @RequestMapping("/hello") // http://localhost:8181/spring/hello
    public String hello() {
        System.out.println("\n ======== 헬로 요청이 들어옴! =======");
        // 어떤 JSP를 열어줄지 경로를 표시
        return "hello";
    }

    @RequestMapping("/food")
    public String asdasd() {
        return "chap01/food";
    }

    // ============ 요청 파라미터 읽기(Query String parameter) ======= //
    // == 1. HttpServletRequest 사용하기
    // ==> ex)  /spring/person?name=kim&age=30
    @RequestMapping("/person")
    public String person(HttpServletRequest request) {
        String name = request.getParameter("name");
        String age = request.getParameter("age");

        System.out.println("name = " + name);
        System.out.println("age = " + age);
        return "";
    }

    // == 2. @RequestParam 사용하기
    // ==> ex)  /spring/major?stu=kim&major=business&grade=3
    @RequestMapping("/major")
    public String major(
            // 파라미터의 이름과 같고, 기본값 설정도 없다면 @RequestParam 생략 가능
            String stu,
            // 파라미터의 변수이름을 바꾸려면 어떤 파라미터인지 알려줘야함
            @RequestParam("major") String mj,
            // 넘어온 파라미터가 없을 경우 기본값을 설정 할수 있음
            @RequestParam(defaultValue = "1") int grade
    ) {
        System.out.println("stu = " + stu);
        System.out.println("major = " + mj);
        System.out.println("grade = " + grade);
        return "";
    }

}
