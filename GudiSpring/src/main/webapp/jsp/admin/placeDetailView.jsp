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
  href="${pageContext.request.contextPath}/css/admin/placeDetailAndUpdate.css" />
<script defer type="text/javascript"
  src="${pageContext.request.contextPath}/js/movePage/movePageFncs.js"></script>
</head>
<body>
<c:if test="${sessionScope.adminDto == null}">
    <c:redirect url="/admin/error"/>
  </c:if>

<div id="main-container">
  <jsp:include page="./nav.jsp"></jsp:include>
  
  <div class="main-container__content">
  
    <div class="place-info">
      <form action="./update?placeNo=${placeDto.placeNo}" method="post">
        <div class="place-info__update">
          <input type="submit" value="수정" class="btn" />
        </div>
        
        <!-- placeNo, placeName -->
        <div class="place-info__list">
          <div class="place-info__content">
            <label class="text--black text--bold">장소번호</label>
            <input name="placeNo" value="${placeDto.placeNo}" readonly class="text--black width--sm" />
          </div>
          <div class="place-info__content">
            <label class="text--black text--bold">장소이름</label>
            <input name="placeName" value="${placeDto.placeName}" class="text--black width--md" />
          </div>
        </div> <!-- place-place-info__list -->
        
        <!-- category, plPhone -->
        <div class="place-info__list">
          <div class="place-info__content">
            <label class="text--black text--bold">카테고리</label>
            <input name="category" value="${placeDto.category}" readonly class="text--black width--sm" />
          </div>
          <div class="place-info__content">
            <label class="text--black text--bold">전화번호</label>
            <input name="plPhone" value="${placeDto.plPhone}" class="text--black width--md" />
          </div>
        </div>
        
        <!-- plAddress -->
        <div class="place-info__list">
          <div class="place-info__content">
            <label class="text--black text--bold">주소</label>
            <input name="plAddress" value="${placeDto.plAddress}" class="text--black width--lg" />
          </div>
        </div>
        
        <!-- phWebsite, genRerervation, recoRerervation -->
        <div class="place-info__list">
          <div class="place-info__content">
            <label class="text--black text--bold">웹 사이트</label>
            <input name="plWebsite" value="${placeDto.plWebsite}" class="text--black width--md" />
          </div>
          
          <div class="place-info__content">
            <label class="text--black text--bold">일반 예약</label>
            <input name="genRerervation" value="${placeDto.genRerervation}" readonly class="text--black width--sm" />
          </div>
          
          <div class="place-info__content">
            <label class="text--black text--bold">추천 예약</label>
            <input name="recoRerervation" value="${placeDto.recoRerervation}" readonly class="text--black width--sm" />
          </div>
        </div>
        
        <!-- img -->
        <div class="place-info__list">
          <div class="place-info__content">
            <div class="text--black img--lg">이미지 대신 임시 텍스트 사용 중</div>
          </div>
        </div>     
      </form>
    </div> <!-- place-info -->
  
  </div>
</div> <!-- main-container -->
</body>
</html>