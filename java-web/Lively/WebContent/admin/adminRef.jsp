<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
function call(adminEmail){
	//alert("자바스크립트 수행");
	ret = confirm("삭제");
	if(ret) 
		location.href="userDelete.do?adminEmail="+adminEmail;
 }
</script>

</head>
<body>
<h1>member 목록</h1>

<table border="1">
<tr>
	<td>ADMINNO</td>    
	<td>ADMINEMAIL</td> 
	<td>ADMINPASS</td> 
	<td>ADMINLEVEL</td>
</tr>
<c:forEach items="${table.table_name}List" var="admin" >
	<td><a href="userDelete.do?adminEmail=${admin.adminEmail}"></a>${admin.adminEmail}</td>
	<td>${admin.adminEmail}</td>
	<td>${admin.adminPass}</td>
	<td>${admin.adminLevel}</td>
	<td><button onclick="call(${admin.adminEmail});">Delete</button></td>
</c:forEach>

</table>
</body>
</html>