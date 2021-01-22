<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/friend/list.jsp</title>
<!-- 상대경로로 부트스트랩 css요청 -->
<!--
	주의할 점 실제 이클립스 project explorer에서 볼수있는 파일의 경로 말고 run했을 때 뜨는 주소창의 위치가 실제 list.jsp의 위치이다. 
	실제로 주소창에는 http://localhost:8888/spring03/friend/list.do 이렇게 떠있다.
	따라서  ../로 한번만 나가야한다. 
-->
<link rel="stylesheet" href="../resources/css/bootstrap.css" />
</head>
<body>
<div class="container">
	<h1>친구 목록입니다.</h1>
	<ul>
		<!-- requestScope.list에서 requestScope는 생략이 가능하다. -->
		<c:forEach var="tmp" items="${list }">
			<li>${tmp }</li>
		</c:forEach>
	</ul>
	<!-- 상대경로 -->
	<a href="../home.do">인덱스로 가기</a>
	<!-- 절대경로로 최상위경로 요청 web.xml의 welcome-file-list가 최상위경로 -->
	<a href="${pageContext.request.contextPath }/"></a>
</div>
</body>
</html>