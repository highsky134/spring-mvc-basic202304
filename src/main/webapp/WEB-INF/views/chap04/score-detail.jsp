<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    section {
        padding: 10px;
        width: 45%;
        
        margin: 200px auto; 
        border: 2px solid orange;
        border-radius: 5px;
        box-shadow: 3px 3px 5px orange;
    }
    li {
        list-style: none;
    }
    a {
        background: greenyellow;
        color: #fff;
        border: 1px solid #fff;
        border-radius: 2px;
        box-shadow: 2px 2px 3px gold;
        
    }
</style>
</head>
<body>
    <section>
        <div>
            <h1>${s.name}님의 성적 정보</h1>
        </div>
        <div>
            <ul>
                <li># 국어: <span class="score">${s.kor}점</span></li>
                <li># 영어: <span class="score">${s.eng}점</span></li>
                <li># 수학: <span class="score">${s.math}점</span></li>
                <li># 총점: ${s.total}점</li>
                <li># 평균: ${s.average}점</li>
                <li># 등급: ${s.grade}점</li>
            </ul>
        </div>
        <div>
            <a href="/score/list">목록</a>
            <a id="update-score" href="/score/updateForm?stuNum=${s.stuNum}">수정</a>
        </div>
    </section>
        
    
</body>
</html>