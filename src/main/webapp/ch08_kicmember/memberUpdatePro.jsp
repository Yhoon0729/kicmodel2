<%@page import="ch08.KicMember"%>
<%@page import="ch08.KicMemberDAO"%>
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
	String id = (String)session.getAttribute("id");
	String pass = request.getParameter("pass");
	String name = request.getParameter("name");
	int gender = Integer.parseInt(request.getParameter("gender"));
	String tel = request.getParameter("tel");
	String email = request.getParameter("email");
	
	KicMemberDAO dao = new KicMemberDAO();
	KicMember memdb = dao.getMember(id);
	
	KicMember kic = new KicMember(); // DTO bean
	kic.setId(id);
	kic.setPass(pass);
	kic.setName(name);
	kic.setGender(gender);
	kic.setTel(tel);
	kic.setEmail(email);
	
	String msg = "";
	String url = "memberUpdateForm.jsp";
	
	if (memdb != null) {
		if (memdb.getPass().equals(pass)) {
			msg = "수정 완료";
			dao.updateMember(kic);
			url = "joinInfo.jsp";
		} else {
			msg = "비밀번호가 틀렸습니다.";
			url = "memberUpdateForm.jsp";
		} // end of if (memdb.getPass().equals(pass))
	} else {
		msg = "수정할 수 없습니다.";
	} // end of if (memdb != null)
	%>
	<script>
		alert("<%=msg %>");
		location.href = ("<%=url %>");
	</script>
</body>
</html>