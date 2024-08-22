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
  href="${pageContext.request.contextPath}/css/admin/admin/adminList.css" />
<script defer type="text/javascript"
  src="${pageContext.request.contextPath}/js/movePage/movePageFncs.js"></script>
<script defer type="text/javascript"
  src="${pageContext.request.contextPath}/js/admin/navFocus.js"></script>
<script defer type="text/javascript"
  src="${pageContext.request.contextPath}/js/admin/authoritycheck.js"></script>
</head>
<body>

  <c:if test="${sessionScope.adminDto == null}">
    <c:redirect url="/admin/error" />
  </c:if>

  <jsp:include page="../nav.jsp"></jsp:include>

  <div id="main-container">

    <div class="main-container__content">

      <!-- TODO: 검색 -->
      <div class="admin__select">
        <form action="./list" method="post">
          <input type="hidden" name="formName" value="searchAdminForm"/>
          <label for="admin__search" class="admin__select--title">관리자
            검색</label> <input id="admin__search" name="search" value="${searchKeyword}"
            placeholder="관리자의 이름이나 닉네임, 아이디를 작성해주세요" />
          <button type="submit" class="btn" name="action"
            value="searchAdminsForm">검색</button>
        </form>     
      </div>

      <!-- 전체로 다시 넘어가는 버튼 -->
      <div class="btn-container">
        <button onclick="moveAdminListPageFnc('${pageContext.request.contextPath}');" class="btn">전체</button>
      </div>

      <div class="admin__list">  
        <ul>
          <li>
            <div class="admin__list--col text--black width--m">이름</div>
            <div class="admin__list--col text--black width--md">닉네임</div>
            <div class="admin__list--col text--black width--md">아이디</div>
            <div class="admin__list--col text--black width--lg">가입일</div>
            <div class="user__list--col text--black width--sm">게시글 수</div>
            <div class="user__list--col text--black width--sm">댓글 수</div>
            <div class="admin__list--col text--black width--sm">권한</div>
          </li>

          <c:if test="${empty adminList}">
            <li class="admin__list--error">검색하신 관리자를 찾을 수 없습니다.</li>
          </c:if>

          <c:if test="${not empty adminList}">
            <c:forEach var="adminDto" items="${adminList}">
              <li>
                <div class="text--black width--m text--point" onclick="showAdminCheckForm('${adminDto.userNo}');">${adminDto.name}</div>
                <div class="text--black width--md">${adminDto.nickname}</div>
                <div class="text--black width--md">${adminDto.id}</div>
                <div class="text--black width--lg">${adminDto.creDate}</div>
                <div class="text--black width--sm">${adminDto.postCount}</div>
                <div class="text--black width--sm">${adminDto.commentCount}</div>
                <div class="text--black width--sm">${adminDto.authority}</div>
              </li>
            </c:forEach>
          </c:if>
        </ul>
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
    
    <form id="adminCheckForm" method="post" action="${pageContext.request.contextPath}/admin/list" style="display: ${not empty errorMsg ? 'block' : 'none'};">
      <span class="adminCheckForm--exist" onclick="hideAdminCheckForm()">닫기</span>
      <input type="hidden" name="formName" value="adminCheckForm"/>
      <input type="hidden" id="adminCheckNo" name="adminNo" value="${not empty adminNo ? adminNo : ''}">
      <!-- 비밀번호 입력 필드 -->
      <div class="adminCheckForm__title">
        <div>정보를 확인하려는 계정의 비밀번호를 입력하세요.</div>
      </div>
      <div id="adminPasswordInput">
        <input type="password" id="password" name="password" placeholder="비밀번호를 입력하세요" required>
      </div>
      <c:if test="${not empty errorMsg}">
        <div class="pwd-error" style="color: red;">
          ${errorMsg}
        </div>
      </c:if>
      <div class="btn-container">
        <button onclick="submitForm();" class="btn">확인</button>
      </div>
    </form>
    
    
    
  </div>
  <!-- main-container -->
</body>
</html>