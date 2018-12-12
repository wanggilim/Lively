<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- 자주쓰는 경로의 절대 경로 간편하게 설정하기 -->
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>


<jsp:include page="header.jsp"></jsp:include>
<a href="member/login.do">임시 atag 로그인</a><br>
<h1>#######검색창#######</h1>

test test

<p>첫 화면</p>
</body>
</html>