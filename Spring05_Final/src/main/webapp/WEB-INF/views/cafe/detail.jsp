<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/cafe/detail.jsp</title>
<%--include는 반드시 상대경로로 --%>
<jsp:include page="../include/resource.jsp"></jsp:include>
</head>
<body>
<jsp:include page="../include/navbar.jsp">
	<jsp:param value="cafe" name="thisPage"></jsp:param>
</jsp:include>
<div class="container">
	<%--depth를 줄 수 있다. --%>
	<nav>
		<ul class="breadcrumb">
			<li class="breadcrumb-item">
				<a href="${pageContext.request.contextPath }/">Home</a>
			</li>
			<li class="breadcrumb-item">
				<a href="${pageContext.request.contextPath }/cafe/list.do">글 목록</a>
			</li>
			<li class="breadcrumb-item active">상세보기</li>
		</ul>
	</nav>
	<table class="table table-bordered">
		<tr>
			<th>글 번호</th>
			<td>${dto.num }</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${dto.writer }</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${dto.title }</td>
		</tr>
		<tr>
			<th>조회수 </th>
			<td>${dto.viewCount }</td>
		</tr>
		<tr>
			<th>등록일</th>
			<td>${dto.regdate}</td>
		</tr>
		<tr>
			<td colspan="2">
				<div>${dto.content}</div>
			</td>
		</tr>
	</table>

	<ul>
		<li><a href="list.do">목록보기</a></li>
		<%--로그인 해야지만 수정, 삭제가 가능하게끔~ --%>
		<%--session에 있는 id와  글작성자가 같을 때 수정, 삭제가 가능하다. --%>
		<%--id.equals(dto.getWriter())로 하게될경우 id가 null이면
		 nullpointexception으로 500번 버스를 탈 수 있다.--%>
		<c:if test="${dto.writer eq sessionScope.id }">
			<li><a href="private/update_form.do?num=${dto.num }">수정</a></li>
			<li><a href="javascript:deleteConfirm()">삭제</a></li>		
		</c:if>		
	</ul>
</div>
<script>
	function deleteConfirm(){
		let isDelete=confirm("글을 삭제하시겠습니까?");
		if(isDelete){
			location.href="private/delete.do?num=${dto.num}";
		}
	}
</script>
</body>
</html>