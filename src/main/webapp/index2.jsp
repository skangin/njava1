<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewpot" content="width=device-width">
<title>페이지2 접근형태 - sessionStorage + get파라미터</title>
</head>
<body>
<input type="button" value="클릭" onclick="abc()">
</body>
<script>
	var today = "";
	var data = "";
	
	today = Date.now();
	data = sessionStorage.setItem("time",today);
	console.log(today);
	
	function abc(){
		window.open("./index3.jsp?day="+today,"","");
		location.href="./login.html";
		
	}
</script>
</html>