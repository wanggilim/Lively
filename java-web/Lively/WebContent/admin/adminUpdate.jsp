<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC ">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>관리자 권한 수정</h1>
<form action="adminUpdate.do" method="POST">
관리자 번호: <input type="text" name ="adminNo" value="${adminNo }" readonly />
관리자 메일: <input type="text" name="adminEmail" value="${adminEmail }" readonly />
관리자 권한: <input type="number" name="adminLevel" value="${adminLevel }" readonly/>
변경할 관리자 권한: <input type="number" name="setAdminLevel" min=1 max= 3 value="${adminLevel }"/>

<input type="submit" value="수정하기" />
</form>
</body>
</html>