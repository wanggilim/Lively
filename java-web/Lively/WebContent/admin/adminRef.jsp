<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>
<h1> Member 목록</h1>
<table border="1">
<tr>
	<td>USERNO</td>    
	<td>USERMAIL</td> 
	<td>USERPASS</td> 
	<td>USERLEVEL</td>
	<td>GENDER</td>
	<td>BIRTHDAY</td>
</tr>


<c:forEach items="${memberList}" var="member" >
<tr>
	<td>${member.userNo}</td>
	<td>${member.userMail}</td>
	<td>${member.userPass}</td>
	<td>${member.userLevel}</td>
	<td>${member.gender}</td>
	<td>${member.birthday}</td>
	<td>
	<form action="userDelete.do?" method="Post" onsubmit="memberDelete()">
	<input type="hidden" name="userNo" value="${member.userNo}">
	<input type="hidden" name="userMail" value="${member.userMail}">
	<input type="hidden" name="userPass" value="${member.userPass}">
	<input type="hidden" name="userLevel" value="${member.userLevel}">
	<input type="hidden" name="gender" value="${member.gender}">
	<input type="hidden" name="birthday" value="${member.birthday}">
	<input type="submit" value="Delete">
	</form>
	</td>
	<td>
	<form action="adminUpdate.do" onsubmit="return memberUpdate();" method="Post">
	<input type="hidden" name="userNo" value="${member.userNo}">
	<input type="hidden" name="userMail" value="${member.userMail}">
	<input type="hidden" name="userPass" value="${member.userPass}">
	<input type="hidden" name="userLevel" value="${member.userLevel}">
	<input type="hidden" name="gender" value="${member.gender}">
	<input type="hidden" name="birthday" value="${member.birthday}">
	<input type="submit" value="Update" >					
	</form>
	</td>
</tr>
</c:forEach>

</table>
</body>
</html>