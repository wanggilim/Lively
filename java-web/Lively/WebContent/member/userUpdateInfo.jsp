<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- //jquery library -->
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>나의 정보 수정</title>
<!-- //jquery library -->
<script src="../js/jquery-3.3.1.min.js"></script>
<script src="/Lively/member/js/signUp.js"></script> 
</head>
<body>
<h1> 회원 정보 수정 </h1>
<form action="userUpdate.do" method ="POST" onsubmit="return userUpdateAjax();">
<table>
<tr>
<td>회원 넘버: <span id="userPass">${member.userNo }</span></td>
</tr>
<tr>
<td>회원 메일: ${member.userMail }</td>
</tr>
<tr>
<td>회원 비번: ${member.userPass }</td> 
</tr>
<tr>
<td>변경할 비밀번호:<input type="password" id="" name="setUserPass" value=""></td>
</tr>
<tr>
<td>회원 성별: ${member.gender }</td>
</tr>
<tr>
<td>변경할 성별:<input type="text" name="setUserGender" value=""></td>
</tr>
<tr>
<td>회원 생년월일: ${member.birthday }</td>
</tr>
<tr>
<td>변경할 생년월일:<input type="date" name="setUserBirthday" value=""></td>
</tr>
<tr>
<td>회원 권한: ${member.userLevel }</td>
</tr>
</table>
소셜 로그인 동의: 
<select name="connectSNS">
	<option value="yes">연결</option>
	<option value="no">연결해제</option>
</select>
<input type="submit" value="등록" >
<input type="reset" value="초기화">
</form>

</body>
</html>