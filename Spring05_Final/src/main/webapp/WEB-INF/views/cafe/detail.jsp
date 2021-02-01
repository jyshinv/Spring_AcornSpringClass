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
<style>
	/* 프로필 이미지를 작은 원형으로 만든다 */
	#profileImage{
		width: 50px;
		height: 50px;
		border: 1px solid #cecece;
		border-radius: 50%;
	}
</style>
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
	
	<!-- 댓글 목록 -->
	<div class="comments">
		<ul>
			<c:forEach var="tmp" items="${commentList }">
				<li>
					<dl>
						<dt>
						<c:choose>
							<c:when test="${empty tmp.profile }">
								<svg id="profileImage" xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person" viewBox="0 0 16 16">
					  				<path d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0zm4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4zm-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10z"/>
								</svg>
							</c:when>
							<c:otherwise>
								<img id="profileImage" 
									src="${pageContext.request.contextPath }${tmp.profile}" />
							</c:otherwise>
						</c:choose>			
						</dt>
						<dd>
							<pre>${tmp.content }</pre>
						</dd>
					</dl>
				</li>
			</c:forEach>
		</ul>
	</div>
	
	<!-- 원글에 댓글을 다는 폼 -->
	<div class="comment_form">
		<form action="private/comment_insert.do" method="post">
			<!-- 원글의 글번호가 ref_group 번호가 된다.-->
			<input type="hidden" name="ref_group" value="${dto.num }" />
			<!-- 원글의 작성자가 댓글의 수신자가 된다. -->
			<input type="hidden" name="target_id" value="${dto.writer }" />
			<textarea name="content"></textarea>
			<button type="submit">등록</button>
		</form>
	</div>
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