<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/upload.jsp</title>
</head>
<body>
<p> 원본 파일명 : <strong>${requestScope.orgFileName }</strong></p>
<p> 저장된 파일명 : <strong>${requestScope.saveFileName }</strong></p>
<p> 파일의 크기 : <strong> ${requestScope.fileSize }</strong> byte</p>
<p> 파일이 저장된 경로 : <strong>${requestScope.path }</strong></p>
<p> 입력한 제목 : <strong>${requestScope.title }</strong></p>
</body>
</html>