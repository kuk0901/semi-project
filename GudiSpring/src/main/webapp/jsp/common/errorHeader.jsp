<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="header">

  <c:if test="${sessionScope.userDto.authority eq 'user' || sessionScope.userDto == null}">
    <div class="logo-container">
      <a class="logo-container--logo" href="${pageContext.request.contextPath}">DwY</a>
    </div>
  </c:if>
  
  <c:if test="${sessionScope.userdto.authority eq 'admin'}">
    <div class="logo-container">
      <a class="logo-container--logo" href="${pageContext.request.contextPath}/admin" style="width: 73px;">Admin</a>
    </div>
  </c:if>

</div>