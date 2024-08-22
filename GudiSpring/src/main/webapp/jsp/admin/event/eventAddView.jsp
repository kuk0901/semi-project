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
  href="${pageContext.request.contextPath}/css/admin/event/addEvent.css" />
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
  
      <div class="btn-container">
        <button class="btn" onclick="moveAdminEventListPageFnc('${pageContext.request.contextPath}');">돌아가기</button>
      </div>
  
      <!-- event add form -->
      <div class="event-form">
        <form action="./add" method="post">

          <div class="event-form__content">
            <label class="text--black text--bold width--lg">이벤트 명</label> 
            <input name="eventName" class="text--black width--lg" />
          </div>

          <!-- TODO: 시분초 추가할 지 고민 -->
          <div class="event-form__content">
            <label class="text--black text--bold width--sm">시작일</label> 
            <input type="date" name="openDate" class="text--black width--sm" />
          </div>

          <div class="event-form__content">
            <label class="text--black text--bold width--sm">종료일</label> 
            <input type="date" name="closeDate"  class="text--black width--sm" />
          </div>

          <!-- submit button -->
          <input type="submit" value="추가" class="btn btn--submit" />
        </form>
        
        <div class="description">
          <div class="description__title">
            이벤트 등록 시 주의사항
          </div>
          <ul class="description__list">
            <li class="description__list--content">
              <span>상세 페이지에서 확인 가능한 당첨자 EVENT는 <u>임시 당첨자</u>입니다.</span>
            </li>
            <li class="description__list--content">
              <span>이벤트 시작일 및 종료일의 <u>상세 시간</u>은 자정(00:00:00) 임을 주의하세요.</span>
             </li>
            <li class="description__list--content">
              <span>이벤트 당첨자는 <u>한 명</u>만 설정할 수 있으니 설정 전 한 번 더 확인하시길 바랍니다.</span>
            </li>
          </ul>
        </div>
        
      </div><!-- event add form -->

    </div>
      <!-- main-container__content -->
  </div>
  <!-- main-container -->

</body>
</html>