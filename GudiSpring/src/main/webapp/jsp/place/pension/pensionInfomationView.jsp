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
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/common.css" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/place/areaAndPlace.css" type="text/css" />
<script defer src="${pageContext.request.contextPath}/js/movePage/movePageFncs.js"></script>
</head>
<body>
	<jsp:include page="/jsp/common/header.jsp" />

	<!-- pension information -->
	<div class="main-container">
		<div class="main-container_content">
			<div class="image-container">
				<div class="image-container--img">이미지 준비중</div>
			</div>
			<div class="info-container">
				<ul>
					<li class="placeName">${pension.placeName}</li>
					<li>Address : ${pension.plAddress}</li>
					<li>Phone : ${pension.plPhone}</li>
					<li><a href="${pension.plWebsite}" target="_blank" class="place--webSite">Web : <span>${pension.plWebsite}</span></a></li>
				</ul>
			</div>
		</div>
	</div>

	<jsp:include page="/jsp/common/footer.jsp" />
</body>
</html>