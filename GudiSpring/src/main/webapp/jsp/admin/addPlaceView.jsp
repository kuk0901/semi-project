<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dog With You Admin</title>
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
  href="${pageContext.request.contextPath}/css/admin/addPlacePage.css" />
<script defer type="text/javascript"
  src="${pageContext.request.contextPath}/js/movePage/movePageFncs.js"></script>
</head>
<body>
  <c:if test="${sessionScope.adminDto == null}">
    <c:redirect url="/admin/error" />
  </c:if>

  <div id="main-container">
    <jsp:include page="./nav.jsp"></jsp:include>

    <div class="main-container__content">

      <div class="place-form">
        <form id="placeAddForm" action="./add" method="post">
          <!-- select line -->
          <div class="select-container">
            <!-- category -->
            <div class="place-form__content">
              <label class="text--black">카테고리</label>
              <select name="category" class="text--black">
                <option value="펜션" selected="selected" class="text--black">펜션</option>
                <option value="식당" class="text--black">식당</option>
                <option value="카페" class="text--black">카페</option>
              </select>
            </div>

            <!-- area -->
            <div class="place-form__content">
              <label class="text--black">지역</label>
              <select name="areaNo" class="text--black">
                <option value="1" selected="selected" class="text--black">경기도, 서울</option>
                <option value="2" class="text--black">강원도</option>
                <option value="3" class="text--black">충청도, 대전, 세종</option>
                <option value="4" class="text--black">전라도, 광주</option>
                <option value="5" class="text--black">경상도, 대구, 울산, 부산</option>
                <option value="6" class="text--black">제주도</option>
              </select>
            </div>
          </div>

          <div class="place-form__content">
            <label class="text--black">이름</label> <input name="placeName" class="text--black" />
          </div>

          <div class="place-form__content">
            <label class="text--black">주소</label> <input name="plAddress" class="text--black" />
          </div>

          <div class="place-form__content">
            <label class="text--black">전화번호</label> <input name="plPhone" placeholder="숫자만 입력" class="text--black" />
          </div>

          <div class="place-form__content">
            <label class="text--black">사이트</label> <input name="plWebsite" class="text--black" />
          </div>

          <!-- submit button -->
          <input type="submit" value="추가" class="btn" />
        </form>
      </div> <!-- place-form -->

    </div>
    <!-- main-container__content -->
  </div>
  <!-- main-container -->
</body>
</html>