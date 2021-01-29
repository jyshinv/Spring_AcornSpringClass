<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/users/private/update.jsp</title>
</head>
<body>
<script>
	alert("수정했습니다.");
	//스크립트 코드로 간접적인 리다리엑트를 해주는 격 (리다이렉트 효과를 낼 수 있다.)
	location.href="${pageContext.request.contextPath }/users/private/info.do";
</script>	
</body>
</html>