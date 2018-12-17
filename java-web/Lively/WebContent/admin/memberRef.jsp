<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script>
function call(userEmail){
	//alert("자바스크립트 수행");
	ret = confirm("삭제");
	if(ret) 
		location.href="userDelete.do?userEmail="+userEmail;
 }

</script>

</head>
<body>
<h1> Member 목룍</h1>
<table border="1">
<tr>
	<td>USERNO</td>    
	<td>USERMAIL</td> 
	<td>USERPASS</td> 
	<td>USERLEVEL</td>
	<td>GENDER</td>
	<td>BIRTHDAY</td>
</tr>


<c:forEach items="${table.table_name}List" var="member" >
	<td><a href="userDelete.do?adminEmail=${member.memberEmail}"></a>${member.memberEmail}</td>
	<td>${member.userEmail}</td>
	<td>${member.userPass}</td>
	<td>${member.userLevel}</td>
	<td>${member.gender}</td>
	<td>${member.birthday}</td>
	<td><button onclick="call(${member.userEmail});">Delete</button></td>
</c:forEach>

</table>
</body>
</html>