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
  href="${pageContext.request.contextPath}/css/admin/place/placeList.css" />
<script defer
  src="${pageContext.request.contextPath}/js/common/common.js"></script>
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

      <!-- 검색 -->
      <div class="place__select">
        <form action="./list" method="get">
          <input type="hidden" name="formName" value="searchPlaceForm" />
          <label for="place__search" class="place__select--title">장소
            검색</label> <input id="place__search" name="search"
            placeholder="장소의 이름을 작성해주세요" value="${searchKeyword}" />
          <button type="submit" class="btn" name="action"
            value="searchPlaceForm">검색</button>
        </form>
      </div>

      <!-- 카테고리 별 장소 조회 버튼 + 장소 추가 버튼 -->
      <div class="category-container">
        <form action="./list" method="get">
          <input type="hidden" name="formName" value="selectAll" />
          <button type="submit" class="btn">전체</button>
        </form>

        <form action="./list" method="get">
          <input type="hidden" name="formName" value="selectPension" />
          <button type="submit" class="btn">펜션</button>
        </form>

        <form action="./list" method="get">
          <input type="hidden" name="formName" value="selectRestaurant" />
          <button type="submit" class="btn">식당</button>
        </form>

        <form action="./list" method="get">
          <input type="hidden" name="formName" value="selectCafe" />
          <button type="submit" class="btn">카페</button>
        </form>

        <div class="place__add--btn">
          <button
            onclick="moveAdminAddPlacePageFnc('${pageContext.request.contextPath}');"
            class="btn btn--add">장소 추가</button>
        </div>
      </div>

      <!-- place list -->
      <div class="place__list">
        <form action="./list" method="post"
          onsubmit="return confirm('정말 삭제하시겠습니까?');">
          <div class="place__list--btn">
            <input type="hidden" name="formName"
              value="removePlacesForm" />
            <button name="action" type="submit" class="btn btn--remove"
              value="removePlacesForm">삭제하기</button>
          </div>

          <ul>
            <li>
              <div class="place__list--col text--black width--sm">분류</div>
              <div class="place__list--col text--black width--md">가게
                명</div>
              <div class="place__list--col text--black width--lg">주소</div>
              <div class="place__list--col text--black width--m">전화번호</div>
              <div class="place__list--col text--black width--sm">예약(G)</div>
              <div class="place__list--col text--black width--sm">예약(R)</div>
              <div class="place__list--col text--black width--sm">장소
                삭제</div>
            </li>

            <!-- 검색 결과에 따른 다른 ui -->
            <c:if test="${empty placeList}">
              <li class="place__list--error">검색하신 장소를 찾을 수 없습니다.</li>
            </c:if>

            <c:if test="${not empty placeList}">
              <c:forEach var="placeDto" items="${placeList}">
                <li>
                  <div class="text--black width--sm">${placeDto.category}</div>
                  <div class="text--black width--md text--left">
                    <a
                      href="${pageContext.request.contextPath}/admin/place/detail?placeNo=${placeDto.placeNo}"
                      class="text--black width--md">
                      ${placeDto.placeName} </a>
                  </div>
                  <div class="text--black width--lg text--left">${placeDto.plAddress}</div>
                  <div class="text--black width--m">${placeDto.plPhone}</div>
                  <div class="text--black width--sm">${placeDto.genReservation}</div>
                  <div class="text--black width--sm">${placeDto.recoReservation}</div>
                  <div class="text--black width--sm">
                    <input type="checkbox" name="remove"
                      value="${placeDto.placeNo}" />
                  </div>
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
          <a
            href="?page=${currentPage-1}&formName=${currentFormName}&search=${searchKeyword}"
            class="pagination-prev">이전</a>
        </c:if>

        <c:forEach begin="${startPage}" end="${endPage}" var="i">
          <c:choose>
            <c:when test="${currentPage eq i}">
              <strong class="pagination--cur-page">${i}</strong>
            </c:when>
            <c:otherwise>
              <a
                href="?page=${i}&formName=${currentFormName}&search=${searchKeyword}"
                class="pagination--page">${i}</a>
            </c:otherwise>
          </c:choose>
        </c:forEach>

        <c:if test="${currentPage < totalPages}">
          <a
            href="?page=${currentPage+1}&formName=${currentFormName}&search=${searchKeyword}"
            class="pagination--next">다음</a>
        </c:if>
      </div>

    </div>
    <!-- main-container__content -->
  </div>
  <!-- main-container -->
</body>
</html>