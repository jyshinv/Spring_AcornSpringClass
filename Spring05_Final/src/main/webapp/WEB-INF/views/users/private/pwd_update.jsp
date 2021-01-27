<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/users/private/update.jsp</title>
</head>
<body>
<div class="container">
	<c:choose>
		<c:when test="${requestScope.isSuccess }">
			<p>
				비밀번호를 수정했습니다.
				<a href="${pageContext.request.contextPath }/users/loginform.do">다시 로그인하기</a>
			</p>
		</c:when>
		<c:otherwise>
			<p>이전 비밀번호가 일치하지 않습니다.</p>
			<a href="pwd_updateform.do">다시시도</a>
		</c:otherwise>
	</c:choose>
</div>
<script>
	
</script>	
</body>
</html>