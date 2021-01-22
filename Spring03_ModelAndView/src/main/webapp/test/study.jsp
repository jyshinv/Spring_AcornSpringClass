<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/test/study.jsp</title>
</head>
<body>
<h1>공부 페이지 입니다.</h1>
<p>열심히 공부 하즈아</p>
<!-- 아래 요청은 모두 dispatcherServlet을 거침 -->
<!-- 상대경로 요청 -->
<a href="../home.do">인덱스로</a><br />
<!-- 절대경로 요청 -->
<a href="${pageContext.request.contextPath }/home.do">인덱스로</a><br />
<!-- 절대경로로 최상위경로 요청 web.xml에 welcome file list로 home.do를 설정해놨기 때문에 최상위경로를 요청하면 home.jsp로 간다. -->
<a href="${pageContext.request.contextPath }/">인덱스로</a>
</body>
</html>