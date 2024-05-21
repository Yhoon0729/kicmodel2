<%@page import="ch08.KicMember"%>
<%@page import="ch08.KicMemberDAO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
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
	request.setCharacterEncoding("utf-8");
	String id = request.getParameter("id");
	String pass = request.getParameter("pass");

	// Connection 객체
	KicMemberDAO dao = new KicMemberDAO();
	
	String msg = id + "님으로 로그인 하셨습니다.";
	String url = "index.jsp";
	
	KicMember mem = dao.getMember(id);
	if (mem != null) {
		if (pass.equals(mem.getPass())) {
			session.setAttribute("id", id);
		} else {
			msg = "비밀번호가 틀렸습니다";
			url = "login.jsp";
		} // end of if (pass.equals(dpass))
	} else {
		msg = "존재하지 않는 아이디입니다.";
		url = "login.jsp";
	} // end of if (dpass != null)
	%>

	<script>
		alert("<%=msg%>");
		location.href="<%=url%>";
	</script>
</body>
</html>