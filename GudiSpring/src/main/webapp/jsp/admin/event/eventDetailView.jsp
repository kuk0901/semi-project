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
  href="${pageContext.request.contextPath}/css/admin/event/eventDetail.css" />
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
            <button onclick="moveAdminEventListPageFnc('${pageContext.request.contextPath}');" class="btn">돌아가기</button>
          </div>
        </div>
        <ul class="page-guidelines__list">
          <li class="page-guidelines__list--content">
            <span>이벤트 당첨자의 닉네임 EVENT는 당첨자를 추첨하기 전 임시 당첨자임을 주의하세요.</span>
          </li>
          <li class="page-guidelines__list--content">
            <span>이벤트 당첨자의 닉네임을 변경해야 합니다.</span>
          </li>
          <li class="page-guidelines__list--content">
            <span>당첨자의 닉네임 변경 후 수정 버튼을 클릭할 경우 이름, 아이디, 전화번호는 자동으로 수정됩니다.</span>
          </li>
        </ul>
      </div>
      
      <div class="event-info">
        <form action="./detail?eventNo=${eventMap['eventNo']}"
          method="post" onsubmit="return confirm('정말 수정하시겠습니까?');">
          <div class="event-info__update">
            <input type="submit" value="수정" class="btn" />
          </div>

          <!-- HashMap 사용 -->
          <!-- eventNo, eventName -->
          <div class="event-info__list">
            <div class="event-info__content">
              <label class="text--black text--bold">번호</label> 
              <input name="eventNo" value="${eventMap['eventNo']}" readonly class="text--black width--s" />
            </div>
            <div class="event-info__content">
              <label class="text--black text--bold">이벤트 명</label> 
              <input name="eventName" value="${eventMap['eventName']}" class="text--black width--lg" />
            </div>
          </div>

          <!-- openDate, closeDate -->
          <div class="event-info__list">
            <div class="event-info__content">
              <label class="text--black text--bold">시작일</label> 
              <input type="date" name="openDate" value="${eventMap['openDate']}" class="text--black width--sm" />
            </div>
            <div class="event-info__content">
              <label class="text--black text--bold">종료일</label> 
              <input type="date" name="closeDate" value="${eventMap['closeDate']}" class="text--black width--sm" />
            </div>
          </div>

          <!-- user name, nickname -->
          <div class="event-info__list">
            <div class="event-info__content">
              <label class="text--black text--bold">당첨자 이름</label> 
              <input name="name" value="${eventMap['userName']}" readonly class="text--black width--sm" />
            </div>
            <div class="event-info__content">
              <label class="text--black text--bold">당첨자 닉네임</label> 
              <input name="nickname" value="${eventMap['userNickname']}" class="text--black width--sm" />
            </div>
          </div>

          <!-- user id, phone -->
          <div class="event-info__list">
            <div class="event-info__content">
              <label class="text--black text--bold">당첨자 아이디</label> 
              <input name="id" value="${eventMap['userId']}" readonly class="text--black width--sm" />
            </div>

            <div class="event-info__content">
              <label class="text--black text--bold">당첨자 전화번호</label> 
              <input name="phone" value="${eventMap['userPhone']}" readonly class="text--black width--sm" />
            </div>
          </div>

        </form>
      </div>
      <!-- place-info -->

    </div>
  </div>
  <!-- main-container -->

</body>
</html>