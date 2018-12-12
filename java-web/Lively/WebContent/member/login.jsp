<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인</title>
</head>
<body>
<h1>로그인 페이지</h1>
<a href="member/signup.do">회원가입</a>
				<!-- doGet으로 갖고 온 "사용자 입력값"을 doPost로 보내기  -->
<form action="login.do" method="post">
ID<input type="email" name="userMail" id="userMail"   required="required"/><br>
PASSWORD<input type="password" name="userPass" id="userPass" required="required"/><br>
<input type="submit" value="로그인" ><br>
</form>

<!-- 로그인 실패했을 때  -->
${signMessage}
</body>
</html>