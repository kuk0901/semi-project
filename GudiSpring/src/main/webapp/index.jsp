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
<link rel="stylesheet" href="https://unpkg.com/swiper@8/swiper-bundle.min.css" />
<script src="https://unpkg.com/swiper@8/swiper-bundle.min.js"></script>

<!-- icon -->
<link rel="stylesheet" href="http://fonts.googleapis.com/icon?family=Material+Icons">

<!-- js -->
<script defer src="./js/movePage/movePageFncs.js"></script>
<script defer src="${pageContext.request.contextPath}/js/mainPage/mainPageFncs.js"></script>
</head>
<body>
 
  <!-- header -->
  <jsp:include page="./jsp/common/header.jsp" />

  <!-- main -->
  <div id="main-container">
    <div class="main-container__content">
      <img alt="" src="././img/main/banner.png">
    </div>
    <div class="main-container__content">
      <div class="swiper">
        <div class="swiper-wrapper">
          <div class="swiper-slide">
            <img alt="" src="././img/main/cogi.png">
          </div>
          <div class="swiper-slide">
            <img alt="" src="././img/main/jindo.png">
          </div>
          <div class="swiper-slide">
            <img alt="" src="././img/main/dog1.png">
          </div>
          <div class="swiper-slide">
            <img alt="" src="././img/main/dog2.png">
          </div>
          <div class="swiper-slide">
            <img alt="" src="././img/main/dog3.png">
          </div>
        </div>

        <div class="swiper-pagination"></div>

        <div class="swiper-button-prev">
          <div class="material-icons">arrow_back</div>
        </div>
        <div class="swiper-button-next">
          <div class="material-icons">arrow_forward</div>
        </div>
      </div>
    </div>
    <div class="main-container__content event">메인 구조 확인용3</div>
    <div class="main-container__content event">메인 구조 확인용4</div>
  </div>
  
  <!-- footer -->
  <jsp:include page="./jsp/common/footer.jsp" />

</body>
</html>