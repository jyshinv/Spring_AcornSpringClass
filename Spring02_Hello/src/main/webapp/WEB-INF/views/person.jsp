<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/fortune.jsp</title>
</head>
<body>
<h1>오늘의 인물 페이지</h1>
<!-- requestScope는 생략가능 -->
<p>오늘의 인물 : <strong>${requestScope.personToday }</strong></p>
<!-- .do요청은 모두 appServlet이라는 이름의 Dispatcher Servlet을 거져간다. -->
<a href="home.do">인덱스로 가기</a>
</body>
</html>