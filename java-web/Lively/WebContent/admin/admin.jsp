<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <!DOCTYPE html PUBLIC >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin Page</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>

//전체검색 ajax
$(function(){
	$("#tableName").on("change", function(){
		var tableName = $(this).val();

		$.get("admin.do?tableName="+tableName, function(data,status){
			$("#here").html(data);
			$.get("admin2.do?tableName="+tableName, function(data,status){		
				$("#emailList").html(data);
			},false);
		}, false);
	});
});

//조건검색 함수
function retrieve(){
	var tableName = document.getElementById("tableName").value;

	if(tableName == "admin"){
		alert(tableName);
	var level = document.getElementById("adminLevel").value;
	var mail = document.getElementById("adminEmail").value;
	var param ="adminLevel="+level+"&adminEmail="+mail;
	var xhttp = new XMLHttpRequest();
  	xhttp.onreadystatechange = function() { //onreadystatechange은 콜백함수로 서버에 다녀오면 function()이라는 익명함수가 정의한 로직을 수행한다.
    if (this.readyState == 4 && this.status == 200) { //this.readyState == 4 && this.status == 200 서버에서 갔다고 도착했고 정상적으로 끝났는지 확인
      document.getElementById("here").innerHTML = this.responseText;// here라는 id를 가진 곳에 this.responseText의 결과 값을 넣어버림
    }
  };
  xhttp.open("GET","adminRef2.do?"+param); //get방식으로 보낸다.
 
}else{
	var userMail = document.getElementById("userMail").value;
	var userLevel = document.getElementById("userLevel").value;
	var param ="userMail="+userMail+"&userLevel="+ userLevel;
	var xhttp = new XMLHttpRequest();
  	xhttp.onreadystatechange = function() { //onreadystatechange은 콜백함수로 서버에 다녀오면 function()이라는 익명함수가 정의한 로직을 수행한다.
    if (this.readyState == 4 && this.status == 200) { //this.readyState == 4 && this.status == 200 서버에서 갔다고 도착했고 정상적으로 끝났는지 확인
      document.getElementById("here").innerHTML = this.responseText;// here라는 id를 가진 곳에 this.responseText의 결과 값을 넣어버림
    }
  };
  xhttp.open("GET","memberRef2.do?"+param); //get방식으로 보낸다.

}
xhttp.send();

}
</script>

</head>
<body>
<h1> 관리자 페이지 </h1>
테이블 명:
<select name="tableName" id="tableName" >
	<option value="0">테이블 선택</option>
	<option value="member">Member</option>	
	<option value="admin">Admin</option>
</select>	
<button><a href="adminInsert.jsp">관리자 등록</a></button>

<div id="emailList"></div>

<div id="here"></div>

</body>
</html>