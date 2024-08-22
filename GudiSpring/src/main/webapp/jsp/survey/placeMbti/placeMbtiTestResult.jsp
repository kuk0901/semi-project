<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 반려견과 어울리는 장소</title>
<link rel="stylesheet"
  href="https://cdn.jsdelivr.net/npm/reset-css@5.0.2/reset.min.css">
<link rel="stylesheet"
  href="${pageContext.request.contextPath}/css/common/common.css"
  type="text/css" />
<link rel="stylesheet"
  href="${pageContext.request.contextPath}/css/survey/placeMbtiTest/result.css">
<script defer
  src="${pageContext.request.contextPath}/js/movePage/movePageFncs.js"></script>
<script defer
  src="${pageContext.request.contextPath}/js/survey/placeMbtiTest/validation.js"></script>

<!-- icon -->
<link rel="stylesheet"
  href="http://fonts.googleapis.com/icon?family=Material+Icons">
</head>
<body>
  <jsp:include page="/jsp/common/header.jsp" />

  <div id="main-container">
    <div class="cube material-icons">pets</div>
    <div class="cube material-icons">pets</div>
    <div class="cube material-icons">pets</div>
    <div class="cube material-icons">pets</div>
    <div class="cube material-icons">pets</div>
    <div class="cube material-icons">pets</div>
    <div class="cube material-icons">pets</div>
    <!-- main-container__content -->
    <div class="main-container__content">
      <div class="test-result">
        <c:forEach var="placeDto" items="${placeList}">
          <div class="place-container">
            <div class="place-medal">
              <div class="front">
                <img src="/img/place/${placeDto.placeImgPath}"
                  alt="${placeDto.placeName}" class="place--img" />
              </div>
              <div class="back">
                <div class="place--title">"${placeDto.placeName}"</div>
                <div class="place-address">${placeDto.plAddress}</div>
                <button 
                  class="btn btn-res" 
                  onclick="userValidationFnc(
                    '${pageContext.request.contextPath}', 
                    '${placeDto.areaNo}', 
                    '${placeDto.placeNo}', 
                    '${not empty sessionScope.userDto.userNo ? sessionScope.userDto.userNo : ''}'
                    );"
                 >
                  예약하러 가기
                </button>
              </div>
            </div>
          </div>
        </c:forEach>
      </div>
      <div class="guidement">
        <div class="material-icons icon">star</div>
        <div class="comment">이미지에 마우스를 올려보세요!</div>
      </div>
    </div>
    <!-- main-container__content -->
  </div>
  <!-- main-container -->

  <jsp:include page="/jsp/common/footer.jsp" />
</body>
</html>