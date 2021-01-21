<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/home.jsp</title>
</head>
<body>
<h1>인덱스 페이지 입니다.</h1>
<ul>
	<!-- web.xml의 servlet-mapping태그 확인하기 .do요청을 하면 appServlet이 요청을 처리한다. -->
	<li><a href="fortune.do">오늘의 운세</a></li>
	<li><a href="person.do">오늘의 인물</a></li>
</ul>

</body>
</html>