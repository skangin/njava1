<%@page import="java.util.Arrays"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//일반 jsp로 받는 형태
	//controller attribute 사용시 자료형에 맞는 구조를 생성하여 getattribute로 로드하는 것이 중요
	ArrayList<String> al = (ArrayList<String>)request.getAttribute("person_list");
	
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring 3일차 - Controller -> View로 배열값 출력</title>
</head>
<body>
<p>고객명 리스트<p>
<ul>
<%
int w=0;
while(w<al.size()){
%>
<li><%=al.get(w) %></li>
<%
w++;
}
%>
</ul>
</body>
</html>