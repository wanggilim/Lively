<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script>
//멤버삭제
function memeberDelete(userNo){
	//alert("자바스크립트 수행");
	ret = confirm("삭제");
	if(ret) 
		location.href="memberDelete.do?userNo="+userNo;
 }
 
function memberUpdate(userNo, userPass, userMail, userLevel, gender, birthday){
	 ret = confirm("수정하시겠습니까?");
	 if(ret)  
		location.href="memberUpdate.do?userNo="+userNo
					+"&userPass="+userPass
					+"&userMail="+userMail
					+"&userLevel="+userLevel
					+"&gender="+gender
					+"&birthday="+birthday;
}
 
 //멤버수정

</script>

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
	<td><button onclick="memberDelete((${member.userNo}');">Delete</button></td>
	<td><button onclick="memberUpdate('${member.userNo}',
										'${member.userPass}',
										'${member.userMail }',
										'${member.userLevel }',
										'${member.gender }',
										'${member.birthday }')">
										Update
										</button>	
	
</tr>
</c:forEach>

</table>
</body>
</html>