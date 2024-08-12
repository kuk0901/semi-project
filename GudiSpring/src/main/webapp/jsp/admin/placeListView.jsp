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
  href="${pageContext.request.contextPath}/css/admin/placeListPage.css" />
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
  
    <!-- 검색 -->
    <div class="place__select">
      <form action="./place" method="get">
        <input type="hidden" name="formName" value="searchPlaceForm"/>
        <label for="place__search" class="place__select--title">장소 검색</label> 
        <input id="place__search" name="search" placeholder="장소의 이름을 작성해주세요" />
        <button type="submit" class="btn" name="action" value="searchUsersForm">검색</button>
      </form>
    </div>
  
    <!-- 카테고리 별 장소 조회 버튼 + 장소 추가 버튼 -->
    <div class="category-container">
      <form action="./place" method="get">
        <input type="hidden" name="formName" value="selectAll"/>
        <button type="submit" class="btn">전체</button>
      </form>
      
      <form action="./place" method="get">
        <input type="hidden" name="formName" value="selectPension"/>
        <button type="submit" class="btn">펜션</button>
      </form>
      
      <form action="./place" method="get">
        <input type="hidden" name="formName" value="selectRestaurant"/>
        <button type="submit" class="btn">식당</button>
      </form>
      
      <form action="./place" method="get">
        <input type="hidden" name="formName" value="selectCafe"/>
        <button type="submit" class="btn">카페</button>
      </form>
      
      <div class="place__add--btn">        
          <button onclick="moveAdminAddPlacePageFnc('${pageContext.request.contextPath}');" class="btn btn--add">장소 추가</button>
      </div>
    </div>
  
    <!-- place list -->
    <div class="place__list">
      <form action="./place" method="post">
        <div class="place__list--btn">
          <input type="hidden" name="formName" value="removePlacesForm" />
          <button name="action" type="submit" class="btn btn--remove" value="removePlacesForm">삭제하기</button>
        </div>
        
        <ul>
          <li>
            <div class="place__list--col text--black width--sm">분류</div>
            <div class="place__list--col text--black width--md">가게 명</div>
            <div class="place__list--col text--black width--lg">주소</div>
            <div class="place__list--col text--black width--md">전화번호</div>
            <div class="place__list--col text--black width--sm">예약(G)</div>
            <div class="place__list--col text--black width--sm">예약(R)</div>
            <div class="place__list--col text--black width--sm">장소 삭제</div>      
          </li>
  
          <!-- 검색 결과에 따른 다른 ui -->
          <c:if test="${empty placeList}">
            <li class="place__list--error">
              검색하신 장소를 찾을 수 없습니다.
            </li>
          </c:if> 
  
          <c:if test="${not empty placeList}">
            <c:forEach var="placeDto" items="${placeList}">
              <li>
                <div class="text--black width--sm">${placeDto.category}</div>
                <div class="text--black width--md">
                  <a href="${pageContext.request.contextPath}/admin/place/update?placeNo=${placeDto.placeNo}" class="text--black width--md">
                    ${placeDto.placeName}
                   </a>
                </div>
                <div class="text--black width--lg">${placeDto.plAddress}</div>
                <div class="text--black width--md">${placeDto.plPhone}</div>
                <div class="text--black width--sm">${placeDto.genRerervation}</div>
                <div class="text--black width--sm">${placeDto.recoRerervation}</div>
                <div class="text--black width--sm">
                  <input type="checkbox" name="remove" value="${placeDto.placeNo}" />
                 </div>
              </li>
            </c:forEach>
          </c:if>
          
        </ul>
      </form>
    </div> <!-- user__list -->
  
  </div>
</div> <!-- main-container -->
</body>
</html>