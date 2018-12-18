<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
function adminDelete(adminNo){
	ret = confirm("삭제하시겠습니까?");
	if(ret) 
		location.href="adminDelete.do?adminNo="+adminNo;
 }
 
 function adminUpdate(adminNo, adminPass, adminEmail, adminLevel){
	 ret = confirm("수정하시겠습니까?");
 	 if(ret)  
 		location.href="adminUpdate.do?adminNo="+adminNo
 					+"&adminPass="+adminPass
 					+"&adminEmail="+adminEmail
 					+"&adminLevel="+adminLevel;
 }
</script>

</head>
<body>
<h1>Admin 목록</h1>
<table border="1">
<tr>
	<td>ADMINNO</td>
	<td>ADMINEMAIL</td>     
	<td>ADMINPASS</td> 
	<td>ADMINLEVEL</td>
	
</tr>


<c:forEach items="${adminList}" var="admin" >
<tr>
	<td>${admin.adminNo}</td>
	<td>${admin.adminEmail}</td>
	<td>${admin.adminPass}</td>
	<td>${admin.adminLevel}</td>
	<td><button onclick="adminDelete('${admin.adminNo}')">Delete</button></td>
	<td><button onclick="adminUpdate('${admin.adminNo}',
										'${admin.adminPass}',
										'${admin.adminEmail }',
										'${admin.adminLevel }')">
										Update
										</button>
</tr>
</c:forEach>
</table>
</body>
</html>