<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/reset-css@5.0.2/reset.min.css"
  rel="stylesheet">
<link rel="stylesheet"
  href="${pageContext.request.contextPath}/css/common/common.css" />
<link rel="stylesheet"
  href="${pageContext.request.contextPath}/css/auth/signup.css" />
<script defer src="${pageContext.request.contextPath}/js/common/common.js"></script>
<script defer
  src="${pageContext.request.contextPath}/js/movePage/movePageFncs.js"></script>
<script defer
	src="${pageContext.request.contextPath}/js/util/signupDuplecateFncs.js"></script>
</head>
<body>
  <jsp:include page="/jsp/common/header.jsp" />

<!-- toast container -->
  <c:if test="${not empty msg}">
    <div id="toast" class="toast" data-message="${msg}"></div>
  </c:if>

  <div id="main-container">

    <div class="main_signUp">
      <h1 class="main_signUp--title">
		<span>S</span>
		<span>I</span>
		<span>G</span>
		<span>N</span>
		<span>U</span>
		<span>P</span>
       </h1>
      <div class="main_signUp_content_form">
        <!-- signup form -->
        <form id="mainSignupForm" action="./signup" method="post"
          class="main_signUp_content" name="frm">

          <div class="main_signUp_content__item">
            <!-- 이름 -->
            <div class="main_signUp_content_element">
              <label for="userName">이름</label>
              <div class="signUp_content_element--textBox">
                <input id="userName" type="text" value="${userDto.name}"
                  name="userName">
              </div>
            </div>

            <!-- 닉네임 -->
            <div class="main_signUp_content_element">
              <label for="userNickname">닉네임</label>
              <c:choose>
                <c:when test="${nickResult==1}">
                  <span class="errorMessage">중복된
                    닉네임입니다</span>
                  <script>
                         document.getElementById("userNickname").focus();
                  </script>
                </c:when>
                <c:when test="${nickResult==-1}">
                  <span class="errorMessage">사용가능한 닉네임입니다</span>
                </c:when>
              </c:choose>
              <div class="signUp_content_element--textBox">
                <input id="userNickname" type="text"
                  value="${userDto.nickname}" class="nicknameCheckBox"
                  name="nickname"> <input type="button"
                  value="중복확인" onclick="nicknameCheckAndSubmit();" />
              </div>
            </div>

            <!-- 아이디 -->
            <div class="main_signUp_content_element">
              <label for="userId">아이디</label>
              <c:choose>
                <c:when test="${idResult==1}">
                  <span class="errorMessage">중복된 아이디입니다</span>
                  <script>
                      document.getelementbyid("userid").focus();
                  </script>
                </c:when>
                <c:when test="${idResult==-1}">
                  <span class="errorMessage">사용가능한 아이디입니다</span>
                </c:when>
              </c:choose>
              <div class="signUp_content_element--textBox">
                <input id="userId" type="text" value="${userDto.id}"
                  class="idCheckBox" name="id"> <input
                  type="button" value="중복확인"
                  onclick="idCheckAndSubmit();" />
              </div>
            </div>

            <!-- 패스워드 -->
            <div class="main_signUp_content_element">
              <label for="userPassword">패스워드</label>
              <div class="signUp_content_element--textBox">
                <input id="userPassword" type="password"
                  value="${userDto.password}" name="password">
              </div>
            </div>

            <!-- 패스워드확인 -->
            <div class="main_signUp_content_element">
              <label for="userPassword-retype">패스워드확인</label> <span
                id="passWordError" class="errorMessage"></span> <span
                id="passWordSuccess" class="errorMessage"></span>
              <div class="signUp_content_element--textBox">
                <input id="userPassword-retype" type="password"
                  onkeyup="passWordRetypeFnc()">
              </div>
            </div>

            <!-- 휴대폰번호 -->
            <div class="main_signUp_content_element">
              <label for="phone">휴대폰번호</label>
              <div class="signUp_content_element--textBox">
                <input id="phone" type="text" value="${userDto.phone}"
                  name="phone">
              </div>
            </div>

            <div class="signUp_content_element--signUpBtn">
              <input type="button" value="회원가입"
                onclick="signupCheckAndSubmit();" />
            </div>
          </div>
          <!-- main_signUp_content__item -->

        </form>
        <!-- signup form -->

      </div>
      <!-- main_signUp_content_form -->

      <!-- 닉네임 유효성 검사 -->
      <form id="duplicateNicknameForm" action="./signup" method="get">
        <input type="hidden" name="checkFrm" value="nickname" id="checkNickname"> 
        <input type="hidden" name="checkUserName" value="userName" id="checkUserNameForNn">
        <input type="hidden" name="checkNickname" value="userNickname" id="checkNicknameForNn"> 
        <input type="hidden" name="checkId" value="userId" id="checkUserIdForNn"> 
        <input type="hidden" name="checkUserPassword" value="userPassword" id="checkUserPasswordForNn"> 
        <input type="hidden" name="checkUserPhone" value="userPhone" id="checkUserPhoneForNn">
      </form>

      <!-- 아이디 유효성 검사 -->
      <form id="duplicateIdForm" action="./signup" method="get">
        <input type="hidden" name="checkFrm" value="id" id="checkUserId">
        <input type="hidden" name="checkUserName" value="username" id="checkUserNameForId"> 
        <input type="hidden" name="checkNickname" value="nickname" id="checkNicknameForId">
        <input type="hidden" name="checkId" id="checkUserIdForId">
        <input type="hidden" name="checkUserPassword" value="userpassword" id="checkUserPasswordForId"> 
        <input type="hidden" name="checkUserPhone" value="userphone" id="checkUserPhoneForId">
      </form>
    </div>
    <!-- main_signUp -->

  </div>
  <!-- main-container -->

  <jsp:include page="/jsp/common/footer.jsp" />
</body>
</html>