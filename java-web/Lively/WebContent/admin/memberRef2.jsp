<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


사용자 이메일:
<input type="text" name="userMail" id="userMail"/>

사용자 등급: <select name="userLevel" id="userLevel"> 
	<option value="0">모든 등급</option>
	<option value="4">4</option>
	<option value="5">5</option>
	<option value="6">6</option>
	<option value="7">7</option>
	<option value="8">8</option>
	<option value="9">9</option>
	<option value="10">10</option>
</select>
<button onclick="retrieve()">조회</button>
