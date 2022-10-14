<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// session에 저장된 cart 제거하기
	session.removeAttribute("cart");

	// 
	response.sendRedirect("03_cart_list.jsp");
%>