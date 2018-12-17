<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>

//선언적함수 
function call(empid){
	 ret = confirm("삭제?");
	 if(ret)
	    location.href='empDelete.go?empid=' + empid;	 
}

function retrieve(){
	 if(event.keyCode != 13) return;


	 var dept = document.getElementById("department_id").value;
	 var job = document.getElementById("job_id").value;
	 var salary = document.getElementById("salary").value;
	 var param = "deptid="+dept+"&jobid=" + job+"&salary=" + salary;
	 
	 var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	      document.getElementById("here").innerHTML = this.responseText;
	    }
	  };
	  xhttp.open("GET", "emplist2.go?"+param);
	  xhttp.send();
	  
	 
}

</script>
</head>
<body>

</body>
</html>