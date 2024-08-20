<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="header">

  <div class="logo-container">
    <a class="logo-container--logo" href="${pageContext.request.contextPath}">DwY</a>
  </div>
 
  <div class="category-container">
    <button onclick="movePensionAreaPageFnc('${pageContext.request.contextPath}');" class="btn">펜션</button>
    <button onclick="moveRestaurantAreaPageFnc('${pageContext.request.contextPath}');" class="btn">식당</button>
    <button onclick="moveCafeAreaPageFnc('${pageContext.request.contextPath}');" class="btn">카페</button>
  </div>
  
  <div class="board-container">
    <button onclick="moveFreeBoardListPageFnc('${pageContext.request.contextPath}');" class="btn">게시판</button>
  </div>
 
  
  <c:if test="${sessionScope.userDto == null}">
    <div class="auth-container">
      <button id="auth_signin" class="btn btn--auth btn--left" onclick="moveSigninPageFnc('${pageContext.request.contextPath}');">SignIn</button>
      <button id="auth_signup" class="btn btn--auth" onclick="moveSignupPageFnc('${pageContext.request.contextPath}');">SignUp</button>
    </div>
  </c:if> 
  
  <c:if test="${sessionScope.userDto.authority eq 'user'}">
    <div class="auth-container">
      <button id="user_page" class="btn btn--auth btn--left">My</button>
      <button id="auth_signout" class="btn btn__auth btn__signout" onclick="moveLogoutPageFnc('${pageContext.request.contextPath}');">SignOut</button>
    </div>
  </c:if>

</div>