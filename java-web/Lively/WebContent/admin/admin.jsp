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
$(function(){
	$("#tableName").on("change", function(){
		var tableName = $(this).val();
		
		get("tableName.do?table_name="+tableName, function(data,status){
			$("#here").html(data);
		})
	});
});

function retrieve(){
	
	 var mailNo = document.getElementById("mailNo").value;
	 var levelNo =  document.getElementById("levelNo").value;
	 var param = "mailNo="+mailNo+"&levelNo="+levelNo;
	 
		var xhttp = new XMLHttpRequest();
	  	xhttp.onreadystatechange = function() { //onreadystatechange은 콜백함수로 서버에 다녀오면 function()이라는 익명함수가 정의한 로직을 수행한다.
	    if (this.readyState == 4 && this.status == 200) { //this.readyState == 4 && this.status == 200 서버에서 갔다고 도착했고 정상적으로 끝났는지 확인
	      document.getElementById("here").innerHTML = this.responseText;// here라는 id를 가진 곳에 this.responseText의 결과 값을 넣어버림
	    }
	  };

	  alert(param);
	  xhttp.open("GET","admin.do?"+param); 
	  xhttp.send();
}

</script>

</head>
<body>
<h1> 관리자 페이지 </h1>
테이블 명:
<select name="tableName" id="tableName" >
	<option value="0">테이블 선택</option>
	<c:forEach var="table" items="${tableList}">
		<option value="${table.table_name }">${table.table_name}</option>
	</c:forEach>
</select>

사용자 이메일:
<select name="mailNo" id="mailNo">
	<option value="0">등록된 이메일 </option>
	<c:forEach var="tbl" items="${table.table_name}List">
		<option value="${tbl.tbl}No">${tbl.tbl}Email</option>
	</c:forEach>
</select>
<button onclick="retrieve()">조회</button>
사용자 권한:
<select name="levelNo" id="levelNo">
	<option value="0">권한 레벨</option>
	<c:forEach var="tbl" items="${table.table_name}List">
		<option value="${tbl.tbl}No"> ${tbl.tbl}level</option>	
	</c:forEach>
</select>

<div id="here"></div>

</body>
</html>