<%@page import="ch08.KicMember"%>
<%@page import="ch08.KicMemberDAO"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
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
	String name = request.getParameter("name");
	int gender = Integer.parseInt(request.getParameter("gender"));
	String tel = request.getParameter("tel");
	String email = request.getParameter("email");
	
	KicMemberDAO dao = new KicMemberDAO();
	KicMember kic = new KicMember(); // DTO bean
	kic.setId(id);
	kic.setPass(pass);
	kic.setName(name);
	kic.setGender(gender);
	kic.setTel(tel);
	kic.setEmail(email);
	
	int num = dao.insertMember(kic);
	
	String msg = "";
	String url = "";
	
	if(num > 0) {
		msg = name + "님의 회원가입이 완료되었습니다.";
		url = "login.jsp";
	} else {
		msg = "회원가입이 실패 하였습니다.";
		url = "join.jsp";
	}
	%>

	<script>
		alert("<%=msg %>");
		location.href="<%=url %>";
	</script>
	
</body>
</html>