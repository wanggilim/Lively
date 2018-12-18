<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>관리자 등록</title>
</head>
<body>

<h1> 관리자 등록  </h1>
<form action="adminInsert.do" >
관리자 이메일: <input type="text" name="adminEmail" >
관리자 비밀번호: <input type="text" name="adminPass" >
<input type="submit" value="등록">
</form>

</body>
</html>