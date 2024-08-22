<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fc"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DWY Admin</title>
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
  href="${pageContext.request.contextPath}/css/admin/main.css" />
<script defer src="${pageContext.request.contextPath}/js/common/common.js"></script>
<script defer type="text/javascript"
  src="${pageContext.request.contextPath}/js/movePage/movePageFncs.js"></script>
<script defer type="text/javascript"
  src="${pageContext.request.contextPath}/js/admin/navFocus.js"></script>
</head>
<body>

  <c:if test="${sessionScope.adminDto == null}">
    <c:redirect url="/admin/error" />
  </c:if>

  <!-- toast container -->
  <c:if test="${not empty msg}">
    <div id="toast" class="toast" data-message="${msg}"></div>
  </c:if>

  <jsp:include page="./nav.jsp"></jsp:include>

  <div id="main-container">

    <div class="main-container__content">

      <!-- TODO: 검색 -->
      <div class="user__select">
        <form action="./main" method="post">
          <input type="hidden" name="formName" value="searchUsersForm" />
          <label for="user__search" class="user__select--title">회원
            검색</label> <input id="user__search" name="search" value="${searchKeyword}"
            placeholder="회원의 이름이나 닉네임, 아이디를 작성해주세요" />
          <button type="submit" class="btn" name="action"
            value="searchUsersForm">검색</button>
        </form>     
      </div>

      <!-- 전체로 다시 넘어가는 버튼 -->
      <div class="btn-container">
        <button onclick="moveAdminMainPageFnc(8090);" class="btn">전체</button>
      </div>

      <div class="user__list">
        <form action="./main" method="post" onsubmit="return confirm('정말 삭제하시겠습니까?');">
          <div class="user__list--btn">
            <input type="hidden" name="formName" value="removeUsersForm" />
            <button name="action" type="submit" class="btn btn--remove"
              value="removeUsersForm">삭제하기</button>
          </div>
          <ul>
            <li>
              <div class="user__list--col text--black width--m">이름</div>
              <div class="user__list--col text--black width--md">닉네임</div>
              <div class="user__list--col text--black width--md">아이디</div>
              <div class="user__list--col text--black width--lg">가입일</div>
              <div class="user__list--col text--black width--sm">게시글 수</div>
              <div class="user__list--col text--black width--sm">댓글 수</div>
              <div class="user__list--col text--black width--sm">권한</div>
              <div class="user__list--col text--black width--sm">탈퇴 신청</div>
              <div class="user__list--col text--black width--sm">회원 체크</div>
            </li>

            <c:if test="${empty userList}">
              <li class="user__list--error">검색하신 사용자를 찾을 수 없습니다.</li>
            </c:if>

            <c:if test="${not empty userList}">
              <c:forEach var="userDto" items="${userList}">
                <li>
                  <div class="text--black width--m">${userDto.name}</div>
                  <div class="text--black width--md">${userDto.nickname}</div>
                  <div class="text--black width--md">${userDto.id}</div>
                  <div class="text--black width--lg">${userDto.creDate}</div>
                  <div class="text--black width--sm">${userDto.postCount}</div>
                  <div class="text--black width--sm">${userDto.commentCount}</div>
                  <div class="text--black width--sm">${userDto.authority}</div>
                  <div class="text--black width--sm">${userDto.userLeave}</div>
                  <div class="text--black width--sm">
                    <input type="checkbox" name="remove"
                      value="${userDto.userNo}" />
                  </div> <!-- 회원 삭제 체크 버튼 -->
                </li>
              </c:forEach>
            </c:if>

          </ul>
        </form>
      </div>
      <!-- user__list -->
    
      <!-- pagination -->
      <div class="pagination">
        <c:if test="${currentPage > 1}">
            <a href="?page=${currentPage - 1}&search=${searchKeyword}" class="pagination-prev">이전</a>
        </c:if>
    
        <c:forEach begin="${startPage}" end="${endPage}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <strong class="pagination--cur-page">${i}</strong>
                </c:when>
                <c:otherwise>
                    <a href="?page=${i}&search=${searchKeyword}" class="pagination--page">${i}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    
        <c:if test="${currentPage < totalPages}">
            <a href="?page=${currentPage + 1}&search=${searchKeyword}" class="pagination--next">다음</a>
        </c:if>
      </div>
    
    </div>
    <!-- main-container__content  --> 
  </div>
  <!-- main-container -->
</body>
</html>