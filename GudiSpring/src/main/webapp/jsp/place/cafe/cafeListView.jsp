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

	<!-- cafe list -->
	<div class="main-container">
		<div class="main-container__content">
			<ul>
				<c:forEach var="cafeDto" items="${cafeList}">
					<li>
						<form action="${pageContext.request.contextPath}/place/cafe/infomation" method="get">
						<input type="hidden" name="placeNo" value="${cafeDto.placeNo}" />
						<button type="submit" class="areaBtn">${cafeDto.placeName}</button>			
						</form>					
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>

	<jsp:include page="/jsp/common/footer.jsp" />
</body>
</html>