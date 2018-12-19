<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!DOCTYPE html PUBLIC >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1> 회원 정보 수정 </h1>
<table>
<tr>
<td>회원 넘버: ${member.userNo }</td>
</tr>
<tr>
<td>회원 메일: ${member.userMail }</td>
</tr>
<tr>
<td>회원 비번: ${member.userPass }</td> 
</tr>
<tr>
<td><input type="text" value="변경할 비밀번호"></td>
</tr>
<tr>
<td>회원 성별: ${member.gender }</td>
</tr>
<tr>
<td>회원 생일: ${member.birthday }</td>
</tr>
<tr>
<td>회원 권한: ${member.userLevel }</td>
</tr>
</table>
소셜 로그인 동의: 
<select>
	<option value="yes">연결</option>
	<option value="no">연결해제</option>
</select>

</body>
</html>