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
	
	KicMemberDAO dao = new KicMemberDAO();
	KicMember memdb = dao.getMember(id);
	
	String msg = "";
	String url = "memberDeleteForm.jsp";
	
	if (memdb != null) {
		if (memdb.getPass().equals(pass)) {
			msg = "탈퇴 완료";
			session.invalidate();
			dao.deleteMember(id); 	
			url = "index.jsp";
		} else {
			msg = "비밀번호가 틀렸습니다.";
			url = "memberDeleteForm.jsp";
		} // end of if (memdb.getPass().equals(pass))
	} else {
		msg = "탈퇴할 수 없습니다.";
	} // end of if (memdb != null)
	%>
	
	<script>
		alert("<%=msg %>");
		location.href = ("<%=url %>");
	</script>
</body>
</html>