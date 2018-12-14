<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lively</title>
</head>
<body>

<jsp:include page="/header.jsp"></jsp:include><br>
<h1> ##### 인덱스 파일 ##### </h1><br>
로그인 전
<button onclick="call('/member/signUp.do')">회원가입</button>
<input type="button" onclick="" id="signIn" name="signIn" value="로그인">
<input type="button" id="signUp" name="signUp" value="회원가입">
</body>
</html>