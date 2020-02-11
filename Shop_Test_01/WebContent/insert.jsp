<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="dbpkg.ShopDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="style.css?ver=11" type="text/css">
<script type="text/javascript">
	function check() {
		if (frm.custname.value == "") {
			alert("회원성명 입력하세요!!");
			frm.custname.focus();
			return false;
		}
		if (frm.phone.value == "") {
			alert("회원번호 입력하세요!!");
			frm.phone.focus();
			return false;
		}
		if (frm.address.value == "") {
			alert("회원주소 입력하세요!!");
			frm.address.focus();
			return false;
		}
		if (frm.grade.value == "") {
			alert("회원등급 입력하세요!!");
			frm.grade.focus();
			return false;
		} else if (!(frm.grade.value == "A" || frm.grade.value == "B" || frm.grade.value == "C")) {
			alert("A, B, C 만 입력하세요!!");
			frm.grade.focus();
			return false;
		}
		if (frm.city.value == "") {
			alert("도시코드 입력하세요!!");
			frm.city.focus();
			return false;
		} else if (frm.city.value.length > 2) {
			alert("2자리 까지 입력가능합니다!!");
			frm.city.focus();
			return false;
		}
		document.frm.action = "insertPro.jsp";
		document.frm.method = "post";
		document.frm.submit();
	}
</script>
</head>
<body>
<%
	request.setCharacterEncoding("UTF-8");
	ShopDAO dao = ShopDAO.getInstance();
	
	Date nowTime = new Date();
	SimpleDateFormat sf = new SimpleDateFormat("YYYYMMdd");
	
	int custno = dao.getCustno();
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
		<h2>홈쇼핑 화면 등록</h2>
		<form name="frm">
			<table border="1">
				<tr>
					<th>회원번호(자동생성)</th>
					<td><input type="text" id="custno" name="custno" value="<%=custno %>" readonly="readonly"></td>
				</tr>
				<tr>
					<th>회원성명</th>
					<td><input type="text" id="custname" name="custname"></td>
				</tr>
				<tr>
					<th>회원전화</th>
					<td><input type="text" id="phone" name="phone"></td>
				</tr>
				<tr>
					<th>회원주소</th>
					<td><input type="text" id="address" name="address"></td>
				</tr>
				<tr>
					<th>가입일자</th>
					<td><input type="text" id="joindate" name="joindate" value="<%=sf.format(nowTime) %>" readonly="readonly"></td>
				</tr>
				<tr>
					<th>고객등급[A:VIP, B:일반, C:직원]</th>
					<td><input type="text" id="grade" name="grade"></td>
				</tr>
				<tr>
					<th>도시코드</th>
					<td><input type="text" id="city" name="city"></td>
				</tr>
				<tr>
					<td>
						<input type="button" value="등 록" onclick="check()">&nbsp;
						<input type="button" value="조 회" onclick="location.href='memList.jsp'">&nbsp;
						<input type="reset" value="리 셋">
					</td>
				</tr>
			</table>
		</form>
	</section>
	<footer>
		HRDKOREA &copy;&copy;&copy;&copy;&copy;&copy;&copy;
	</footer>
</body>
</html>