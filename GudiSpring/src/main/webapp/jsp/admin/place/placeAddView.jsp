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
  href="${pageContext.request.contextPath}/css/admin/place/placeAdd.css" />
<script defer
  src="${pageContext.request.contextPath}/js/common/common.js"></script>
<script defer type="text/javascript"
  src="${pageContext.request.contextPath}/js/movePage/movePageFncs.js"></script>
<script defer type="text/javascript"
  src="${pageContext.request.contextPath}/js/admin/previewImage.js"></script>
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
        <button
          onclick="moveAdminPlaceListPageFnc('${pageContext.request.contextPath}');"
          class="btn">돌아가기</button>
      </div>

      <div class="place-form">
        <form action="./add" method="post" enctype="multipart/form-data">

          <!-- select line -->
          <div class="select-container">
            <!-- category -->
            <div class="place-form__content">
              <label class="text--black text--bold width--sm">카테고리</label>
              <select name="category" class="text--black">
                <option value="펜션" selected="selected"
                  class="text--black width--md">펜션</option>
                <option value="식당" class="text--black">식당</option>
                <option value="카페" class="text--black">카페</option>
              </select>
            </div>

            <!-- area -->
            <div class="place-form__content">
              <label class="text--black text--bold width--sm">지역</label>
              <select name="areaNo" class="text--black">
                <option value="1" selected="selected"
                  class="text--black width--md">경기도, 서울</option>
                <option value="2" class="text--black">강원도</option>
                <option value="3" class="text--black">충청도, 대전,
                  세종</option>
                <option value="4" class="text--black">전라도, 광주</option>
                <option value="5" class="text--black">경상도, 대구,
                  울산, 부산</option>
                <option value="6" class="text--black">제주도</option>
              </select>
            </div>
          </div>
          <!-- select-container -->

          <div class="place-form__content">
            <label class="text--black text--bold width--sm">장소이름</label>
            <input name="placeName" class="text--black width--md" />
          </div>

          <div class="place-form__content">
            <label class="text--black text--bold width--sm">주소</label> <input
              name="plAddress" class="text--black width--lg" />
          </div>

          <div class="place-form__content">
            <label class="text--black text--bold width--md">전화번호</label>
            <input name="plPhone" placeholder="숫자만 입력"
              class="text--black width--md" />
          </div>

          <div class="place-form__content">
            <label class="text--black text--bold width--sm">사이트</label>
            <input name="plWebsite" class="text--black width--md" />
          </div>

          <div class="place-form__content">
            <label class="text--black text--bold width--sm">이미지</label>
            <input type="file" name="image" id="fileInput"
              accept="image/*" onchange="previewImage(this);"
              class="text--black" />
            <!-- TODO: 화면 크기 커짐 문제 조율 필요 -->
            <img id="imagePreview" src="#" alt="Image Priview"
              style="display: none; width: 700px; height: 600px; margin: 30px auto; border: 1px solid #fff;" />
          </div>

          <!-- submit button -->
          <input type="submit" value="추가" class="btn btn--submit" />
        </form>
      </div>
      <!-- place-form -->

    </div>
    <!-- main-container__content -->
  </div>
  <!-- main-container -->
</body>
</html>