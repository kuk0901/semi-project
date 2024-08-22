<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link href="https://cdn.jsdelivr.net/npm/reset-css@5.0.2/reset.min.css"
  rel="stylesheet" />
<link rel="stylesheet"
  href="${pageContext.request.contextPath}/css/common/common.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/auth/signin.css" />
<script defer src="${pageContext.request.contextPath}/js/common/common.js"></script>
<script defer
  src="${pageContext.request.contextPath}/js/movePage/movePageFncs.js"></script>
<script defer
	src="${pageContext.request.contextPath}/js/util/signinDuplecateFncs.js"></script>
</head>
<body>
  <jsp:include page="/jsp/common/header.jsp" />

	<!-- toast container -->
  <c:if test="${not empty msg}">
    <div id="toast" class="toast" data-message="${msg}"></div>
  </c:if>

  <div id="main-container">
		<div class="main_signIn">
			<div class="main_signIn--title">
				<h1>
					<span>S</span>
					<span>I</span>
					<span>G</span>
					<span>N</span>
					<span>I</span>
					<span>N</span>
				</h1>
			</div>
			<div class="main_signIn_content_form">
				<form id="loginForm" action="signin" method="post" class="main_signIn_content">
					<div class="main_signIn_content__items">
						<div class="signIn_content--item">
							<label for="userName">아이디</label> 
							<div class="signIn_content_element--textBox">
								<input id="userId" type="text" name="id" value="">
							</div>
						</div>
						<div class="signIn_content--item">
							<label for="userPassword">비밀번호</label> 
							<div class="signIn_content_element--textBox">
								<input id="userPassword" type="password" name="password" value="">
							</div>
						</div>
						<div class="signIn_content--item">
							<input type="button" value="로그인" class="signup_btn" onclick="signInCheckAndSubmit();"/>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div> <!-- main-container -->

  <jsp:include page="/jsp/common/footer.jsp" />
</body>
</html>