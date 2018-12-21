<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>관리자 등록</title>
<!-- //jquery library -->
<script src="../js/jquery-3.3.1.min.js"></script>
<script src="js/signUp.js"></script>
<link rel="stylesheet" href="resources/css/common/styles.css"
	type="text/css"> 
</head>
<body>

<h1> 관리자 등록  </h1>
<form action="../member/adminInsert.do" method="POST">
usermail : <input type="text" name="userMail" id="userMail"><br>
userpass : <input type="password" name="userPass" id="userPass"><br>
userlevel : <input type="number" name="userLevel" min="1" max="10"><br>
gender : <input type = "radio" name="gender" value="M">남자 <input type = "radio" name="gender" value="F">여자<br>
birthday : <input type = "date" name="birthday"><br>
<input type="submit" value="등록">
</form>
<button onclick="history.go(-1)">이전 화면으로 돌아가기</button>

<!-- <form action="../member/signUp.do" >
관리자 이메일   :<input type="text" name="adminEmail" ><br>
관리자 비밀번호:<input type="text" name="adminPass" ><br>
관리자 성별       :<input type ="password" name="userPass" id="userPass"><br>
관리자 생년 월일:<input type = "date" name="birthday"><br>
<input type="submit" value="등록">
</form> -->
</body>
</html>