<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
  href="${pageContext.request.contextPath}/css/admin/admin/adminDetail.css" />
<script defer src="${pageContext.request.contextPath}/js/common/common.js"></script>
<script defer type="text/javascript"
  src="${pageContext.request.contextPath}/js/movePage/movePageFncs.js"></script>
</head>
<body>
  <c:if test="${sessionScope.adminDto == null}">
    <c:redirect url="/admin/error" />
  </c:if>

  <!-- toast container -->
  <c:if test="${not empty msg}">
    <div id="toast" class="toast" data-message="${msg}"></div>
  </c:if>

  <jsp:include page="../nav.jsp"></jsp:include>

  <div id="main-container">

    <div class="main-container__content">

      <div class="page-guidelines">
        <div class="page-guidelines--title__container">
          <div class="page-guidelines--title">수정 관련 안내사항</div>
          <div class="btn-container">
            <button onclick="moveAdminListPageFnc('${pageContext.request.contextPath}');" class="btn">돌아가기</button>
          </div>
        </div>
        <ul class="page-guidelines__list">
          <li class="page-guidelines__list--content">
            <span>관리자 삭제의 경우 현재 페이지에서만 가능합니다.</span>
          </li>
          <li class="page-guidelines__list--content">
            <span>관리자 닉네임은 변경 불가합니다.</span>
          </li>
        </ul>
      </div>
      
      <div class="admin-info">
        <form action="./detail?adminNo=${adminDto.userNo}"
          method="post" onsubmit="return confirm('정말 수정하시겠습니까?');">
          <div class="admin-info__update">
            <input type="submit" value="수정" class="btn" />
            <input type="hidden" name="formName" value="updateAdminForm">
          </div>
 
          <!-- userNo, name, nickname -->
          <div class="admin-info__list">
            <div class="admin-info__content">
              <label class="text--black text--bold">번호</label> 
              <input name="adminNo" value="${adminDto.userNo}" readonly class="text--black width--sm" />
            </div>
            
            <div class="admin-info__content">
              <label class="text--black text--bold">이름</label> 
              <input name="name" value="${adminDto.name}" class="text--black width--sm" />
            </div>
            
            <div class="admin-info__content">
              <label class="text--black text--bold">닉네임</label> 
              <input name="nickname" value="${adminDto.nickname}" readonly class="text--black width--sm" />
            </div>
          </div>

          <!-- id, pwd, phone -->
          <div class="admin-info__list">
            
            <div class="admin-info__content">
              <label class="text--black text--bold">아이디</label> 
              <input name="id" value="${adminDto.id}" class="text--black width--sm" />
            </div>
            
            <div class="admin-info__content">
              <label class="text--black text--bold">비밀번호</label> 
              <input name="pwd" value="${adminDto.pwd}" class="text--black width--sm" />
            </div>
            
            <div class="admin-info__content">
              <label class="text--black text--bold">전화번호</label> 
              <input name="phone" value="${adminDto.phone}" class="text--black width--md" />
            </div>
          </div>

          <!-- creDate -->
          <div class="admin-info__list">
            <div class="admin-info__content">
              <label class="text--black text--bold">계정 생성 날짜</label> 
              <input name="creDate" value="${adminDto.creDate}" readonly class="text--black width--md" />
            </div>
          </div>
          
          <!-- upDate -->
          <div class="admin-info__list">
            <div class="admin-info__content">
              <label class="text--black text--bold">계정 수정 날짜</label> 
              <input name="upDate" value="${adminDto.upDate}" readonly class="text--black width--md" />
            </div>
          </div>
          
          <!-- postCount, commentCount -->
          <div class="admin-info__list">
            <div class="admin-info__content">
              <label class="text--black text--bold">게시글 수</label> 
              <input name="postCount" value="${adminDto.postCount}" readonly class="text--black width--sm" />
            </div>
            
            <div class="admin-info__content">
              <label class="text--black text--bold">댓글 수</label> 
              <input name="commentCount" value="${adminDto.commentCount}" readonly class="text--black width--sm" />
            </div>
          </div>
        </form>
      </div>
      <!-- admin-info -->

      <div class="admin-delete">
        <form action="./detail?adminNo=${adminDto.userNo}" method="post" onsubmit="return confirm('정말 삭제하시겠습니까?')">
           <input type="hidden" name="formName" value="deleteAdminForm" />
           <button class="btn">삭제하기</button>
        </form>
      </div>

    </div>
  </div>
  <!-- main-container -->
</body>
</html>