<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


관리자 메일: 
<input type="text" name="adminEmail" id="adminEmail"/>

관리자 등급: <select name="adminLevel" id="adminLevel">
	<option value="0">모든 등급</option>
	<option value="1">1</option>
	<option value="2">2</option>
	<option value="3">3</option>
</select>
<button onclick="retrieve()">조회</button>


 

 