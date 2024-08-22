<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Place List</title>
<link rel="stylesheet"
  href="https://cdn.jsdelivr.net/npm/reset-css@5.0.2/reset.min.css">
<link rel="stylesheet"
  href="${pageContext.request.contextPath}/css/common/common.css"
  type="text/css" />
<link rel="stylesheet"
  href="${pageContext.request.contextPath}/css/place/place.css"
  type="text/css" />
<script defer src="${pageContext.request.contextPath}/js/movePage/movePageFncs.js"></script>
</head>
<body>
  <jsp:include page="/jsp/common/header.jsp" />

  <!-- pension List  -->
  <div class="main-container">
    <div class="main-container__content">
      <ul class="list-box">
        <c:forEach var="pensionDto" items="${pensionList}">
          <li class="list-box__li"><a
            href="${pageContext.request.contextPath}/place/pension/infomation?placeNo=${pensionDto.placeNo}"
            class="place--list"> <img
              src="/img/place/${pensionDto.plImgPath}" alt="이미지 준비중"
              class="place--img" /> <span class="place--title">${pensionDto.placeName}</span>
          </a></li>
        </c:forEach>
      </ul>
    </div>
  </div>

  <!-- 페이징 네비게이션 -->
  <div class="pagination">
    <c:if test="${currentPage > 1}">
      <a class="pagination-letter"
        href="${pageContext.request.contextPath}/area/place/pension?page=${currentPage - 1}&areaNo=${param.areaNo}">이전</a>
    </c:if>
    <c:forEach var="i" begin="1" end="${totalPages}">
      <c:choose>
        <c:when test="${i == currentPage}">
          <strong class="pagination-nowpage">${i}</strong>
        </c:when>
        <c:otherwise>
          <a class="pagination-number"
            href="${pageContext.request.contextPath}/area/place/pension?page=${i}&areaNo=${param.areaNo}">${i}</a>
        </c:otherwise>
      </c:choose>
    </c:forEach>
    <c:if test="${currentPage < totalPages}">
      <a class="pagination-letter"
        href="${pageContext.request.contextPath}/area/place/pension?page=${currentPage + 1}&areaNo=${param.areaNo}">다음</a>
    </c:if>
  </div>

  <jsp:include page="/jsp/common/footer.jsp" />
</body>
</html>