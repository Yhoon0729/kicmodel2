<%@page import="model.KicMember"%>
<%@page import="dao.KicMemberDAO"%>

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
		String modPass = request.getParameter("modPass");
		
		KicMemberDAO dao = new KicMemberDAO();
		KicMember memdb = dao.getMember(id);
		
		String msg = "";
		String url = "memberPassForm.jsp";
		
		if (memdb != null) {
			if (memdb.getPass().equals(pass)) {
		msg = "수정 완료";
		session.invalidate();
		dao.modifyPass(id, modPass); 	
		url = "login.jsp";
			} else {
		msg = "비밀번호가 틀렸습니다.";
		url = "memberPassForm.jsp";
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