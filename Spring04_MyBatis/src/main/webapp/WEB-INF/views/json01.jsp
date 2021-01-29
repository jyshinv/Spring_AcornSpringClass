<%--json응답할것이기 때문에 application/json으로 고쳐준다. --%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- json형식은 문자열은 ""로 감싸줘야하고 숫자는 ""로 감싸지 않는다. --%>
{"num":${dto.num},"name":"${dto.name}","addr":"${dto.addr}"}