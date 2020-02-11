<%@page import="dbpkg.PriceVO"%>
<%@page import="dbpkg.MemberVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dbpkg.ShopDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="style.css" type="text/css">
</head>
<body>
<%
	request.setCharacterEncoding("UTF-8");
	ShopDAO pr_dao = ShopDAO.getInstance();
	
	ArrayList<PriceVO> prList = pr_dao.prList();
	
%>
	<header>
		<h1>쇼핑몰 회원관리</h1>
	</header>
	<nav>
		<ul>
			<li><a href="insert.jsp">회원등록</a></li>
			<li><a href="memList.jsp">회원목록조회/수정</a></li>
			<li><a href="prList.jsp">회원매출조회</a></li>
			<li><a href="index.jsp">홈으로..</a></li>
		</ul>
	</nav>
	<section>
		<h2>회원목록조회/수정</h2>
		<table border="1">
			<tr>
				<th>회원번호</th>
				<th>성명</th>
				<th>등급</th>
				<th>도시코드</th>
			</tr>
			<%
				for (PriceVO e : prList) {
			%>
			<tr>
				<td><%=e.getCustno() %></td>
				<td><%=e.getCustname() %></td>
				<td><%=e.getGrade() %></td>
				<td><%=e.getTotal_price() %></td>
			</tr>
			<%
				}
			%>
		</table>
	</section>
	<footer>
		HRDKOREA &copy;&copy;&copy;&copy;&copy;&copy;&copy;
	</footer>
</body>
</html>