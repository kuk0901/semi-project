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
  href="${pageContext.request.contextPath}/css/admin/event/eventList.css" />
<script defer
  src="${pageContext.request.contextPath}/js/common/common.js"></script>
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

  <jsp:include page="../nav.jsp"></jsp:include>

  <div id="main-container">
    <div class="main-container__content">

      <!-- TODO: 검색 -->
      <div class="event__select">
        <form action="./list" method="post">
          <input type="hidden" name="formName" value="searchEventsForm" />
          <label for="event__search" class="event__select--title">이벤트
            검색</label> <input id="event__search" name="search"
            placeholder="이벤트 이름을 작성해 주세요" value="${searchKeyword}" />
          <button type="submit" class="btn" name="action"
            value="searchEventsForm">검색</button>
        </form>
      </div>

      <!-- 전체로 다시 넘어가는 버튼 -->
      <div class="btn-container">
        <button
          onclick="moveAdminEventListPageFnc('${pageContext.request.contextPath}');"
          class="btn">전체</button>
      </div>

      <!-- event list -->
      <div class="event__list">
        <form action="./list" method="post"
          onsubmit="return confirm('정말 삭제하시겠습니까?');">
          <div class="event__list--btn">
            <input type="hidden" name="formName"
              value="removeEventsForm" />
            <button name="action" type="submit" class="btn btn--remove"
              value="removeEventsForm">삭제하기</button>
          </div>
          <ul>
            <li>
              <div class="event__list--col width--lg">이벤트
                명</div>
              <div class="event__list--col width--lg">시작일</div>
              <div class="event__list--col width--lg">종료일</div>
              <div class="event__list--col">당첨자 이름</div>
              <div class="event__list--col width--md">당첨자
                닉네임</div>
              <div class="event__list--col">삭제 체크</div>
            </li>

            <c:if test="${empty eventList}">
              <li class="event__list--error">검색하신 이벤트를 찾을 수 없습니다.</li>
            </c:if>

            <c:if test="${not empty eventList}">
              <c:forEach var="eventDto" items="${eventList}">
                <li>
                  <div class="text--black width--lg">
                    <a
                      href="${pageContext.request.contextPath}/admin/event/detail?eventNo=${eventDto.eventNo}"
                      class="text--black width--lg">
                      ${eventDto.eventName} </a>
                  </div>
                  <div class="text--black width--lg">${eventDto.openDate}</div>
                  <div class="text--black width--lg">${eventDto.closeDate}</div>
                  <div class="text--black">${eventDto.userName}</div>
                  <div class="text--black width--md">${eventDto.userNickname}</div>
                  <div class="text--black">
                    <input type="checkbox" name="remove"
                      value="${eventDto.eventNo}" />
                  </div> <!-- 회원 삭제 체크 버튼 -->
                </li>
              </c:forEach>
            </c:if>
          </ul>
        </form>

        <div class="Event__add--btn">
          <button
            onclick="moveAdminAddEventPageFnc('${pageContext.request.contextPath}');"
            class="btn">추가하기</button>
        </div>
      </div>
      <!-- event__list -->

      <div class="pagination">
        <c:if test="${currentPage > 1}">
          <a href="?page=${currentPage - 1}&search=${searchKeyword}"
            class="pagination-prev">이전</a>
        </c:if>

        <c:forEach begin="${startPage}" end="${endPage}" var="i">
          <c:choose>
            <c:when test="${currentPage eq i}">
              <strong class="pagination--cur-page">${i}</strong>
            </c:when>
            <c:otherwise>
              <a href="?page=${i}&search=${searchKeyword}"
                class="pagination--page">${i}</a>
            </c:otherwise>
          </c:choose>
        </c:forEach>

        <c:if test="${currentPage < totalPages}">
          <a href="?page=${currentPage + 1}&search=${searchKeyword}"
            class="pagination--next">다음</a>
        </c:if>
      </div>
    </div>
    <!-- main-container__content -->

  </div>
  <!-- main-container -->
</body>
</html>