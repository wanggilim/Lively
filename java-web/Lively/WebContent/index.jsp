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

<c:if test="${sessionScope.userMail==null}">
로그인 전 <br>
로그인 후 동작이 됩니다.
</c:if>

<c:if test="${sessionScope.userMail!=null}">
로그인 후<br>
</c:if>

<h1>메인 페이지</h1>

</body>
</html>