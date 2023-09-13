<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	List<ArrayList<String>> product_data = (List<ArrayList<String>>)request.getAttribute("product_data");
	int ea = product_data.size();
	DecimalFormat df = new DecimalFormat("###,###");
	//숫자를 자동으로 천단위당 콤마 적용하는 class
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 리스트 페이지</title>
</head>
<body>
<p>등록된 상품갯수 : <%=ea %> 개
<table border="1" width="1000">
<thead>
	<tr>
	<th width="10%">번호</th>
	<th width="15%">상품코드</th>
	<th width="40%">상품명</th>
	<th width="15%">상품가격</th>	
	<th width="20%">수정/삭제</th>	
	</tr>
</thead>
<tbody>
<!-- database에 내용이 출력되는 파트 -->
<%
 	int w =0;
	do{
%>	<tr align="center">
	<td><%=ea-w%></td>
	<td><%=product_data.get(w).get(1)%></td>
	<td align="left"><%=product_data.get(w).get(2)%></td>
	<td><%=df.format(Integer.parseInt(product_data.get(w).get(3)))%>원</td>
	<td>
	<input type="button" value="수정" onclick="modify_pd('<%=product_data.get(w).get(0)%>')">
	<input type="button" value="삭제" onclick="delete_pd('<%=product_data.get(w).get(0)%>')">
	</td>	
	</tr>
	<%
	w++;
	}while(w<ea);

	%>
</tbody>
</table>
</body>
<script>
	function delete_pd(idx) {
		if(confirm("해당 데이터를 삭제하시겠습니까? 절대 복구 안됩니다."))
			location.href='./product_delete.do?idx='+idx;
	}
	function modify_pd(idx) {
		location.href='./product_modify.do?idx='+idx;
	}
</script>
</html>