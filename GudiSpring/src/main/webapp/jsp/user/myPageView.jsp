<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<link href="https://cdn.jsdelivr.net/npm/reset-css@5.0.2/reset.min.css"
  rel="stylesheet" />
<link rel="stylesheet"
  href="${pageContext.request.contextPath}/css/common/common.css" />
<link rel="stylesheet"
  href="${pageContext.request.contextPath}/css/user/myPage.css" />
<script defer
  src="${pageContext.request.contextPath}/js/movePage/movePageFncs.js"></script>
<script defer
  src="${pageContext.request.contextPath}/js/util/signupDuplecateFncs.js"></script>

</head>
<body>

  <jsp:include page="/jsp/common/header.jsp" />

  <div id="main-container">
    <div class="main-container__form">
      <div class="main-container--displayForm">
        <div class="main-container_updateForm">
        
          <div class="main-container_updateForm--title">
            <h1>MY INFO</h1>
          </div>
          
          <div class="main-container--items">
            <form action="${pageContext.request.contextPath}/user/detail" method="post" class="updateForm"
              id="updateForm">
              <input type="hidden" value="${userDto.userNo}"
                name="userNo">
              <div class="main-container--item">
                <label>이름</label>
                <div class="signUp_content_element--textBox">
                  <input type="text" value="${userDto.name}"
                    name="userName" class="userInfo" id="userName"
                    readonly>
                </div>
              </div>
              
              <!-- 닉네임 유효성 검사 -->
              <div class="main-container--item">
                <label>닉네임</label>
                
                <c:choose>
                  <c:when test="${nickResult==1}">
                    <span class="errorMessage">중복된 닉네임입니다</span>
                    <script>
                      document.getElementById(
                          "userNickname").focus();
                    </script>
                  </c:when>
                  <c:when test="${nickResult==-1}">
                    <span class="errorMessage">사용가능한 닉네임입니다</span>
                  </c:when>
                </c:choose>
                
                <div class="signUp_content_element--textBox">
                  <input type="text" value="${userDto.nickname}" name="nickname" id="userNickname" class="userInfo--check"> 
                  <input type="button" value="중복확인" onclick="myPageNicknameCheckAndSubmit();" class="main-container--checkSubmitBtn" />
                </div>
              </div>

              <!-- 아이디 유효성 검사 -->
              <div class="main-container--item">
                <label>아이디</label>
                <c:choose>
                  <c:when test="${idResult==1}">
                    <span class="errorMessage">중복된 아이디입니다</span>
                    <script>
                      document.getelementbyid("userid")
                          .focus();
                    </script>
                  </c:when>
                  <c:when test="${idResult==-1}">
                    <span class="errorMessage">사용가능한 아이디입니다</span>
                  </c:when>
                </c:choose>
                
                <div class="signUp_content_element--textBox">
                  <input type="text" value="${userDto.id}" name="id"
                    id="userId" class="userInfo--check"> <input
                    type="button" value="중복확인"
                    onclick="myPageIdCheckAndSubmit();"
                    class="main-container--checkSubmitBtn" />
                </div>
              </div>

              <div class="main-container--item">
                <label>휴대폰번호</label>
                <div class="signUp_content_element--textBox">
                  <input type="text" value="${userDto.phone}"
                    name="phone" class="userInfo" id="phone">
                </div>
              </div>

              <input type="button" class="main-container--submitBtn"
                value="정보수정" onclick="updateCheckAndSubmit();" />

            </form>
            
          </div>
        </div> <!-- main-container_updateForm -->
      </div> <!-- main-container--displayForm -->
      
      <div class="main-container_myPost">
        <div class="main-container_myPost--title">
          <h1 class="main-container_myPost--titleH1">MYPOST</h1>
        </div>
        
        <div class="main-container_myPost--items">
        <div class="main-container_myPost--itemsTitle">
        	<span>자유게시판</span>
        </div>
          
          <ul>
          	<li class="myPost--li title">
          		<div class="size-s">번호</div>
          		<div class="size-l">제목</div>
          		<div class="size-m">작성일</div>
          		<div class="size-sm">수정 버튼</div>
          	</li>
          
          <c:forEach var="board" items="${myPageBoardList}">
            <li class="myPost--li">
            	<div class="size-s">
            		${board.contentNo}
            	</div>
            	<div class="size-l">
            		${board.contentSubject}
            	</div>
            	<div class="size-m">
            		${board.contentCreDate}
            	</div>
            	<div class="size-sm">
            		<button onclick="moveUserPostDetailPageFnc('${pageContext.request.contextPath}','${board.contentNo}');" class="postUpdateBtn ">수정</button>
            	</div>
            	
            </li>
           </c:forEach>
           </ul>
        </div>
      </div> <!-- main-container_myPost -->

      <form id="duplicateNicknameForm" action="${pageContext.request.contextPath}/auth/signup" method="get">
        <input type="hidden" name="checkFrm" value="myPageNickname" id="checkNickname"> 
        <input type="hidden" name="checkNickname" value="userNickname" id="checkNicknameForNn"> 
        <input type="hidden" name="checkUserName" value="userName" id="checkUserNameForNn">
        <input type="hidden" name="checkId" value="userId" id="checkUserIdForNn"> 
        <input type="hidden" name="checkUserPhone" value="userPhone" id="checkUserPhoneForNn">
      </form>

      <form id="duplicateIdForm" action="${pageContext.request.contextPath}/auth/signup" method="get">
        <input type="hidden" name="checkFrm" value="myPageId" id="checkUserId"> 
        <input type="hidden" name="checkId" id="checkUserIdForId"> 
        <input type="hidden" name="checkUserName" value="username" id="checkUserNameForId">
        <input type="hidden" name="checkNickname" value="nickname" id="checkNicknameForId"> 
        <input type="hidden" name="checkUserPhone" value="userphone" id="checkUserPhoneForId">
      </form>
    </div>
  </div>
</body>
</html>