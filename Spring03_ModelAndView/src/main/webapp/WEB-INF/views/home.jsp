<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- spring mvc project에는 jstl이 default로 들어있다 (dependency로 기본적으로 들어가있음) -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/home.jsp</title>
<!-- 아래 경로에서 cpath 다음 /는 webapp을 의미한다.(jsp에서 WebContent) -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.css" />
</head>
<body>
<div class="container">
	<h1>인덱스 페이지 입니다.</h1>
	<ul>
		<li><a href="test/play.html">놀러가기(DispatcherServlet을 거치치 않음)</a></li>
		<li><a href="test/study.jsp">공부하기(DispatcherServlet을 거치치 않음)</a></li>
		<li><a href="friend/list.do">친구 목록보기</a></li>
		<li><a href="friend/list2.do">친구 목록보기2</a></li>
		<li><a href="${pageContext.request.contextPath }/friend/list3.do">친구 목록보기3</a></li>
		<li><a href="friend/delete.do?num=1">1번 친구 삭제하기</a></li>
		<li><a href="friend/delete.do?num=2">2번 친구 삭제하기</a></li>
		<li><a href="friend/insertform.do">친구추가 폼</a></li>
	</ul>
	<h2>공지 사항 입니다.</h2>
	<ul>
		<c:forEach var="tmp" items="${noticeList }">
			<li>${tmp }</li>
		</c:forEach>
	</ul>
	
</div>
</body>
</html>
