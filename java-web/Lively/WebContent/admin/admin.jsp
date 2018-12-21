<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <!DOCTYPE html PUBLIC >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin Page</title>
<script>

//전체검색 ajax

//조건검색 함수
function retrieve(){
		var userMail = document.getElementById("userMail").value;
		var userLevel = document.getElementById("userLevel").value;
		var param ="userLevel="+userLevel+"&userMail="+userMail;
		var xhttp = new XMLHttpRequest();
  		xhttp.onreadystatechange = function() { //onreadystatechange은 콜백함수로 서버에 다녀오면 function()이라는 익명함수가 정의한 로직을 수행한다.
    	if (this.readyState == 4 && this.status == 200) { //this.readyState == 4 && this.status == 200 서버에서 갔다고 도착했고 정상적으로 끝났는지 확인
      document.getElementById("here").innerHTML = this.responseText;// here라는 id를 가진 곳에 this.responseText의 결과 값을 넣어버림
    }
  };
  xhttp.open("POST","adminInfo.do?"+param); //get방식으로 보낸다.
  xhttp.send();

}

function memberDelete(){
	ret = confirm("삭제");
	if(ret) 
		return true;
	return false;	
 }
 
 
function memberUpdate(){
	 ret = confirm("수정하시겠습니까?");
	 if(ret)  
		return true;
return false;
}


</script>

</head>
<body>
<h1> 관리자 페이지 </h1>
<button><a href="adminInsert.jsp">관리자 등록</a></button>

<p>
메일: 
<input type="text" name="userMail" id="userMail"/>

<p>
등급: <select name="userLevel" id="userLevel">
	<option value="0">모든 등급</option>
	<option value="1">1</option>
	<option value="2">2</option>
	<option value="3">3</option>
	<option value="4">4</option>
	<option value="5">5</option>
	<option value="6">6</option>
	<option value="7">7</option>
	<option value="8">8</option>
	<option value="9">9</option>
	<option value="10">10</option>
</select>
<button onclick="retrieve()">조회</button>

<div id="emailList"></div>

<div id="here"></div>

</body>
</html>