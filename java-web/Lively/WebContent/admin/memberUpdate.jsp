<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>사용자 권한 수정</h1>
<form action="memberUpdate.do" method="POST">
사용자 번호: <input type="text" name ="userNo" value="${userNo }" readonly />
비밀번호: <input type="text" name ="userPass" value="${userPass }" readonly />
사용자 메일: <input type="text" name="userMail" value="${userMail }" readonly />
사용자 권한: <input type="text" name="userLevel" value="${userLevel }" readonly/>
사용자 성별: <input type="text" name="gender" value="${gender }" readonly/>
사용자 생년월일: <input type="text" name="birthday" value="${birthday }" readonly/>
변경할 사용자 권한: <input type="number" name="setMemberLevel" min=4 max=10 value="${userLevel }"/>
<input type="submit" value="수정하기" />
</body>
</html>