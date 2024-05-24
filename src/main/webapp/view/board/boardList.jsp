<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 리스트</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<div class="container">
		<h2 class="text-center">${boardName}[${count}]</h2>
		<a class="btn btn-primary float-right m-2" 
		href="../board/boardForm?boardid=${boardid}">게시판 입력</a>
	
		<table class="table">
			<thead>
				<tr>
					<th>게시글 번호</th>
					<th>작성자</th>
					<th>제목</th>
					<th>이미지</th>
					<th>내용</th>
					<th>작성 시간</th>
					<th>조회</th>
				</tr>
			</thead>
			<tbody>
				<c:set var="count" value="${count}" />
				<c:forEach var="mem" items="${li}">
					<tr>
						<td>${count}</td>
						<c:set var="count" value="${count-1}" />
						<td>${mem.name}</td>
						<td><a href="../board/boardInfo?num=${mem.num}">${mem.subject}</a></td>
						<td>${mem.file1}</td>
						<td>${mem.content}</td>
						<td><fmt:formatDate value="${mem.regdate}" pattern="yyyy-MM-dd HH:mm"/> </td>
						<td>${mem.readcnt}</td>
					</tr>
				</c:forEach>
				</tbody>
		</table>
	</div>
</body>
</html>