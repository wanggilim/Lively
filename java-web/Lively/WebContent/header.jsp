<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="path" value="${pageContext.request.contextPath}"></c:set>

<c:if test="${userMail==null }">
	<a href="${path}/member/signIn.do">로그인</a>
</c:if>
<c:if test="${userMail!=null }">
	<strong>${sessionScope.userMail}</strong>
	<a href="${path}/member/signOut.do"> 로그아웃</a>
</c:if>
