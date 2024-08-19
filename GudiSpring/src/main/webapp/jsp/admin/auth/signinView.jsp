<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="
  https://cdn.jsdelivr.net/npm/reset-css@5.0.2/reset.min.css" rel="stylesheet">
<link rel="stylesheet"
  href="${pageContext.request.contextPath}/css/common/common.css" />
<link rel="stylesheet"
  href="${pageContext.request.contextPath}/css/common/admin.css" />
<link rel="stylesheet"
  href="${pageContext.request.contextPath}/css/admin/auth/signin.css" />
<script defer src="${pageContext.request.contextPath}/js/common/common.js"></script>
<title>DWY Admin SignIn</title>
</head>
<body>
  <!-- toast container -->
  <c:if test="${not empty msg}">
    <div id="toast" class="toast" data-message="${msg}"></div>
  </c:if>
  
  <div id="main-container">

    <div class="main-container__img"></div>

    <div class="main-container__content">
      <div class="content__title">Dog With You</div>
      <div class="content__guideline">Sign in to your account</div>

      <form action="./admin" method="post" class="signin-form">
        <div class="signin-form__content">
          <label for="admin_id">Id</label> 
          <input id="admin_id" name="id" placeholder="관리자 아이디를 입력하세요" />
        </div>
        
        <div class="signin-form__content">
          <label for="admin_pwd">Password</label> 
          <input id="admin_pwd"
            type="password" name="pwd" placeholder="관리자 비밀번호를 입력하세요" />
        </div>
        
        <div class="signin-form__content">
          <button type="submit" class="btn" >Sign In</button>
        </div>
        
        <div class="error-msg">
          <c:out value='${error}' />
        </div>       
      </form>
    </div>
    
  </div> <!-- main container end -->
</body>
</html>