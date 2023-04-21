<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시물 등록</title>
    <style>
        body {
            background-color: #F5E6CC;
            font-family: Arial, sans-serif;
            padding: 20px;
        }
        form {
            width: 80%;
            margin: 0 auto;
        }
        h1 {
            color: #FF9AA2;
            font-size: 32px;
            margin-bottom: 20px;
            text-align: center;
            text-shadow: 2px 2px #FFB7B2;
        }
        label {
            color: #FFB7B2;
            display: block;
            font-size: 24px;
            margin-bottom: 10px;
            text-shadow: 1px 1px #FFD8D6;
        }
        input[type=text], textarea {
            border: none;
            border-radius: 5px;
            box-shadow: 1px 1px #FFB7B2;
            display: block;
            font-size: 20px;
            margin-bottom: 20px;
            padding: 10px;
            width: 100%;
        }
        input[type=submit] {
            background-color: #FF9AA2;
            border: none;
            border-radius: 5px;
            box-shadow: 1px 1px #FFB7B2;
            color: #FFFFFF;
            cursor: pointer;
            font-size: 24px;
            padding: 10px;
            text-shadow: 1px 1px #FFD8D6;
            transition: background-color 0.5s ease;
            width: 100%;
        }
        input[type=submit]:hover {
            background-color: #FFB7B2;
        }
    </style>
</head>
<body>
    <h1>게시물 등록</h1>
    <form action="/board/insert" method="post">
        <label for="title">제목:</label>
        <input type="text" id="title" name="title" required>

        <label for="content">내용:</label>
        <textarea id="content" name="content" rows="10" required></textarea>

        <input type="submit" value="등록">
    </form>
</body>
</html>