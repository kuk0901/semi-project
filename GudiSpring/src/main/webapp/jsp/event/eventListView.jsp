<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Event list</title>
<link rel="stylesheet"
  href="https://cdn.jsdelivr.net/npm/reset-css@5.0.2/reset.min.css">
<link rel="stylesheet"
  href="${pageContext.request.contextPath}/css/common/common.css"
  type="text/css" />
<link rel="stylesheet"
  href="${pageContext.request.contextPath}/css/event/eventList.css"
  type="text/css" />
<script defer
  src="${pageContext.request.contextPath}/js/movePage/movePageFncs.js"></script>
</head>
<body>
  <jsp:include page="/jsp/common/header.jsp" />

  <div id="main-container">
    <div class="main-container__content">
      <!-- event 검색 -->
      <div class="event__select">
        <form action="./list" method="post">
          <label for="event__search" class="event__select--title">이벤트
            검색</label> <input id="event__search" name="search"
            placeholder="이벤트의 이름을 작성해주세요" />
          <button type="submit" class="btn" name="action"
            value="searchEventForm">검색</button>
        </form>
      </div>

      <div class="event__all-select">
        <button onclick="moveEventListPageFnc('${pageContext.request.contextPath}')" class="btn">전체</button>
      </div>

      <div class="event__list">

        <ul>
          <li class="event__list--title">
            <div class="event__list--col text--black width--lg">이름</div>
            <div class="event__list--col text--black">시작일</div>
            <div class="event__list--col text--black">종료일</div>
          </li>

          <c:if test="${empty eventList}">
            <li class="event__list--error">검색하신 이벤트를 찾을 수 없습니다.</li>
          </c:if>

          <c:if test="${not empty eventList}">
            <c:forEach var="eventDto" items="${eventList}">
              <li class="event__list--content">
                <div class="text--black width--lg">
                  <a href="${pageContext.request.contextPath}/event/list/detail?eventNo=${eventDto.eventNo}">
                    ${eventDto.eventName}
                  </a>
                  </div>
                <div class="text--black">${eventDto.openDate}</div>
                <div class="text--black">${eventDto.closeDate}</div>
              </li>
            </c:forEach>
          </c:if>
        </ul>
      </div> <!-- event__list -->
      
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
    </div> <!-- main-container__content -->
  </div> <!-- main-container -->

  <jsp:include page="/jsp/common/footer.jsp" />
</body>
</html>