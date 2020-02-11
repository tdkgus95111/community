<%@page import="dbpkg.MemberVO"%>
<%@page import="dbpkg.ShopDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	request.setCharacterEncoding("UTF-8");
	ShopDAO dao = ShopDAO.getInstance();
	
	MemberVO vo = new MemberVO();
	vo.setCustno(Integer.parseInt(request.getParameter("custno")));
	vo.setCustname(request.getParameter("custname"));
	vo.setPhone(request.getParameter("phone"));
	vo.setAddress(request.getParameter("address"));
	vo.setJoindate(request.getParameter("joindate"));
	vo.setGrade(request.getParameter("grade"));
	vo.setCity(request.getParameter("city"));
	
	int rs = dao.insertMem(vo);
	if(rs == 1) {
%>
<script type="text/javascript">
	alert("등록 성공!!");
	location.href = "memList.jsp";
</script>
<%
	} else {
%>
<script type="text/javascript">
	alert("등록 실패!!");
	history.back();
</script>
<%
	}
%>
</body>
</html>