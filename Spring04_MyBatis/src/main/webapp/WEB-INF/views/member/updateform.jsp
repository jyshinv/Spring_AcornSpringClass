<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="update.do">
		번호<input type="text" name="num" value="${num}" /><br />
		이름<input type="text" name="name" /><br />
		주소<input type="text" name="addr" /><br />
		<button type="submit">확인</button>
		<button type="reset">취소</button>
	</form>
</body>
</html>