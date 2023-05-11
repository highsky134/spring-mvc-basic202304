<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<%@ include file="./include/static-head.jsp" %>
<style>
    #main-title {
        margin-top: 200px;
        font-size: 40px;
        font-weight: 700;
        color: orange;
    }
</style>
</head>
<body>
    <%@ include file="./include/header.jsp" %>

    <%-- session.getAttribute("login") --%>
    <h1 id="main-title">
        <c:if test="${sessionScope.login == null}">
            초보자님 안녕하세요
        </c:if>
        <c:if test="${sessionScope.login != null}">
            ${sessionScope.login.nickName}님 하이룽~
        </c:if>
    </h1>
    <c:if test="${flag}">
        로그인 하셨군요?
    </c:if>
    <script>
        console.log('flag: ${flag}');
    </script>
</body>
</html>