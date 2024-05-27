<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div class="container">
		<div class="input-form-backgroud row">
			<div class="input-form col-md-12 mx-auto">
				<h4 class="mb-3  text-center">게시판 정보</h4>
				<table class="table">
				
				<tr><td>글번호</td><td>${board.num}</td><tr>
				<tr><td>조회수</td><td>${board.readcnt}</td><tr>
				<tr><td>작성자</td><td>${board.name}</td><tr>
				<tr><td>글제목</td><td>${board.subject}</td><tr>
				<tr><td>내용</td><td>${board.content}</td><tr>
				<tr><td>이미지</td><td>
				<img src="${pageContext.request.contextPath}/img/board/${board.file1}" />
				</td>
				<tr>
				
				
				<tr  class="text-center">
				<td colspan="2" >
				<a class="btn btn-primary"
				href=
	"${pageContext.request.contextPath}/board/boardUpdateForm?num=${board.num}">
				글수정</a>
				<a class="btn btn-primary"
	href="${pageContext.request.contextPath}/board/boardDeleteForm?num=${board.num}">
	글삭제</a>
				<a class="btn btn-primary"
	href="${pageContext.request.contextPath}/board/boardList?boardid=${sessionScope.boardid}">
	글목록</a>
				</td>
			</tr>
				
				
				
				
				</table>
			</div>
		</div>
		
		
		
	
	</div>
</body>
</html>