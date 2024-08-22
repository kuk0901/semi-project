<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dog With You</title>
<!-- reset css cdn -->
<link href="https://cdn.jsdelivr.net/npm/reset-css@5.0.2/reset.min.css"
  rel="stylesheet">

<!-- page css -->
<link rel="stylesheet" href="./css/common/common.css">
<link rel="stylesheet" href="./css/event/mainEvent.css">

<!-- swiper -->
<link rel="stylesheet"
  href="https://unpkg.com/swiper@8/swiper-bundle.min.css" />
<script src="https://unpkg.com/swiper@8/swiper-bundle.min.js"></script>

<!-- icon -->
<link rel="stylesheet"
  href="http://fonts.googleapis.com/icon?family=Material+Icons">

<!-- js -->
<script defer src="${pageContext.request.contextPath}/js/main/swipe.js"></script>
<script defer src="${pageContext.request.contextPath}/js/movePage/movePageFncs.js"></script>
</head>
<body>

  <!-- header -->
  <jsp:include page="./jsp/common/header.jsp" />

  <!-- main -->
  <div id="main-container">
    <div class="main-container__content">
      <img alt="" src="./img/main/banner.png" style="width: 100%; height: 300px;">
    </div>
    <div class="main-container__content">
      <div class="swiper">
        <div class="swiper-wrapper">
          <div class="swiper-slide">
            <a href="${pageContext.request.contextPath}/area/place/recommendcafe"> <img
              src="${pageContext.request.contextPath}/img/place/1.jpg"
              alt="이미지 준비중" class="area--img" />
            </a>
          </div>
          <div class="swiper-slide">
            <a href="${pageContext.request.contextPath}/area/place/recommendpension"> <img
              src="${pageContext.request.contextPath}/img/place/2.jpg"
              alt="이미지 준비중" class="area--img" />
            </a>
          </div>
          <div class="swiper-slide">
            <a href="${pageContext.request.contextPath}/area/place/recommendrestaurant"> <img
              src="${pageContext.request.contextPath}/img/place/더바움.jpg"
              alt="이미지 준비중" class="area--img" />
            </a>
          </div>
          <div class="swiper-slide">
            <img src="${pageContext.request.contextPath}/img/place/카페더킹.jpg"
              alt="이미지 준비중" class="area--img" />
          </div>
          <div class="swiper-slide">
            <img
              src="${pageContext.request.contextPath}/img/place/카페이루아.jpg"
              alt="이미지 준비중" class="area--img" />
          </div>
        </div>

        <div class="swiper-pagination"></div>

        <div class="swiper-button-prev">
          <div class="material-icons">arrow_back</div>
        </div>
        <div class="swiper-button-next">
          <div class="material-icons">arrow_forward</div>
        </div>

      </div> <!-- swiper -->
    </div> <!-- main-container__content -->

    <div class="main-container__event">
      <div class="event__main">
        <div class="event__main-box" onclick="moveEventListPageFnc('${pageContext.request.contextPath}');">
          EVENT 당첨자 확인!
        </div>
      </div>
      <div class="event__personality-quiz">
        <div class="event__personality-quiz--fir" onclick="movePlaceMbtiTestPageFnc('${pageContext.request.contextPath}');">
          내 반려견과 어울리는 장소는?!
        </div>
      </div>
    </div>
 
  </div>


  <!-- footer -->
  <jsp:include page="./jsp/common/footer.jsp" />

</body>
</html>