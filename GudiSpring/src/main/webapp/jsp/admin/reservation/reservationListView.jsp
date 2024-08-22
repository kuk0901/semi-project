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
  href="${pageContext.request.contextPath}/css/admin/reservation/reservationList.css" />
<script defer type="text/javascript"
  src="${pageContext.request.contextPath}/js/movePage/movePageFncs.js"></script>
</head>
<body>
  <c:if test="${sessionScope.adminDto == null}">
    <c:redirect url="/admin/error" />
  </c:if>

  <jsp:include page="../nav.jsp"></jsp:include>

  <div id="main-container">

    <div class="main-container__content">

      <!-- 검색 -->
      <div class="reservation__select">
        <form action="./list" method="post">
          <label for="reservation__search" class="reservation__select--title">예약 장소
            검색</label> <input id="reservation__search" name="search"
            placeholder="장소의 이름을 작성해주세요" value="${searchKeyword}" />
          <button type="submit" class="btn">검색</button>
        </form>
      </div>

      <!-- 전체 예약 버튼 -->
      <div class="btn-container">
        <form action="./list" method="get">
          <button type="submit" class="btn">전체</button>
        </form>  
      </div>

      <!-- place list -->
      <div class="reservation__list">
          <ul>
            <li>
              <div class="reservation__list--col width--lg">가게명</div>
              <div class="reservation__list--col width--l">예약날짜</div>
              <div class="reservation__list--col width--l">방문날짜</div>
              <div class="reservation__list--col width--s">분류</div>
              <div class="reservation__list--col width--sm">이름</div>
              <div class="reservation__list--col width--md">닉네임</div>
              <div class="reservation__list--col width--m">전화번호</div>
              <div class="reservation__list--col width--s">인원</div>
            </li>

            <!-- 검색 결과에 따른 다른 ui -->
            <c:if test="${empty reservationList}">
              <li class="reservation__list--error">검색하신 장소의 예약 내역을 찾을 수 없습니다.</li>
            </c:if>

            <c:if test="${not empty reservationList}">
              <c:forEach var="reservationDto" items="${reservationList}">
                <li>
                  <div class="text--black width--lg">${reservationDto.placeName}</div>
                  <div class="text--black width--l">${reservationDto.reservationDate}</div>
                  <div class="text--black width--l">${reservationDto.visitDate}</div>
                  <div class="text--black width--s">${reservationDto.reservationType}</div>
                  <div class="text--black width--sm">${reservationDto.name}</div>
                  <div class="text--black width--md">${reservationDto.nickname}</div>
                  <div class="text--black width--m">${reservationDto.phone}</div>
                  <div class="text--black width--s">${reservationDto.visitorNum}</div>
                </li>
              </c:forEach>
            </c:if>

          </ul>
      </div>
      <!-- user__list -->

      <!-- pagination -->
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