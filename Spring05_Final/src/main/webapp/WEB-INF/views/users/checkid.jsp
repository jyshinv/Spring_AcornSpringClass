<%-- json응답이므로 contentType을 applicion/json으로 변경한다. --%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--컨트롤러에서 responsebody 어노테이션이 있는 코드를 쓰면 지금 이 checkid.jsp 페이지는 없어도 된다. --%>
{"isExist":${isExist}}