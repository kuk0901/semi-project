<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fc"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin</title>
<link
  href="
  https://cdn.jsdelivr.net/npm/reset-css@5.0.2/reset.min.css"
  rel="stylesheet">
<link rel="stylesheet"
  href="${pageContext.request.contextPath}/css/common/common.css" />
<link rel="stylesheet"
  href="${pageContext.request.contextPath}/css/common/admin.css" />
<link rel="stylesheet"
  href="${pageContext.request.contextPath}/css/admin/nav.css" />
<link rel="stylesheet"
  href="${pageContext.request.contextPath}/css/admin/mainPage.css" />
<script defer type="text/javascript"
  src="${pageContext.request.contextPath}/js/movePage/movePageFncs.js"></script>
<script defer type="text/javascript"
  src="${pageContext.request.contextPath}/js/admin/navFocus.js"></script>
</head>
<body>

<div id="main-container">
  <c:if test="${sessionScope.adminDto == null}">
    <c:redirect url="/admin/error"/>
  </c:if>

  <jsp:include page="./nav.jsp"></jsp:include>
  
  <div class="main-container__content">

    <!-- TODO: 검색 -->
    <div class="user__select">
      <form action="./main" method="post">
        <input type="hidden" name="formName" value="searchUsersForm"/>
        <label for="user__search" class="user__select--title">회원 검색</label> 
        <input id="user__search" name="search" placeholder="회원의 이름이나 닉네임, 아이디를 작성해주세요" />
        <button type="submit" class="btn" name="action" value="searchUsersForm">검색</button>
      </form>
    </div>

    <div class="user__list">
      <form action="./main" method="post">
        <div class="user__list--btn">
          <input type="hidden" name="formName" value="removeUsersForm" />
          <button name="action" type="submit" class="btn btn--remove" value="removeUsersForm">삭제하기</button>
        </div>
        <ul>
          <li>
            <div class="user__list--col text--black">이름</div>
            <div class="user__list--col text--black">닉네임</div>
            <div class="user__list--col text--black">아이디</div>
            <div class="user__list--col text--black width--lg">가입일</div>
            <div class="user__list--col text--black">게시글 수</div>
            <div class="user__list--col text--black">댓글 수</div>
            <div class="user__list--col text--black">권한</div>
            <div class="user__list--col text--black">탈퇴 신청</div>
            <div class="user__list--col text--black">회원 체크</div>         
          </li>
  
          <c:if test="${empty userList}">
            <li class="user__list--error">
              검색하신 사용자를 찾을 수 없습니다.
            </li>
          </c:if> 
  
          <c:if test="${not empty userList}">
            <c:forEach var="userDto" items="${userList}">
              <li>
                <div class="text--black">${userDto.name}</div>
                <div class="text--black">${userDto.nickname}</div>
                <div class="text--black">${userDto.id}</div>
                <div class="text--black width--lg">${userDto.creDate}</div>
                <div class="text--black">${userDto.postCount}</div>
                <div class="text--black">${userDto.commentCount}</div>
                <div class="text--black">${userDto.authority}</div>
                <div class="text--black">${userDto.userLeave}</div>
                <div class="text--black">
                  <input type="checkbox" name="remove" value="${userDto.userNo}" />
                 </div> <!-- 회원 삭제 체크 버튼 -->
              </li>
            </c:forEach>
          </c:if>
          
        </ul>
      </form>
    </div> <!-- user__list -->
    
  </div> <!-- main-container__content  -->

</div> <!-- main-container -->
</body>
</html>