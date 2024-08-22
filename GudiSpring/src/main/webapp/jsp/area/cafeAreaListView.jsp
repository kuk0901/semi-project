<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cafeAreaList</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/reset-css@5.0.2/reset.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/common/common.css"
	type="text/css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/area/area.css"
	type="text/css" />
<script defer src="${pageContext.request.contextPath}/js/movePage/movePageFncs.js"></script>
</head>
<body>
	<jsp:include page="/jsp/common/header.jsp" />

	<!-- 카페 지역 -->
	<div class="main-container">
		<div class="main-container__content">
			<ul class="area-list-box">
				<c:forEach var="areaDto" items="${areaList}">
					<li class="area-list-box__li">
						<a href="${pageContext.request.contextPath}/area/place/cafe?areaNo=${areaDto.areaNo}" class="area--list">
						<img src="${pageContext.request.contextPath}/img/area/${areaDto.areaName}카페.png" alt="이미지 준비중" class="area--img" style="width: 330px; height:250px;" /> 
						<span class="area--title"> ${areaDto.areaName} </span>
						</a>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>

	<jsp:include page="/jsp/common/footer.jsp" />

</body>
</html>