<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Restaurant Info</title>
<link rel="stylesheet"
  href="https://cdn.jsdelivr.net/npm/reset-css@5.0.2/reset.min.css">
<link rel="stylesheet"
  href="${pageContext.request.contextPath}/css/common/common.css"
  type="text/css" />
<link rel="stylesheet"
  href="${pageContext.request.contextPath}/css/place/place.css"
  type="text/css" />
<script defer src="${pageContext.request.contextPath}/js/movePage.js"></script>
<style type="text/css">
</style>
<script type="text/javascript">
	
</script>
</head>
<body>
	<jsp:include page="/jsp/common/header.jsp" />

	<!-- restaurant information -->
	<div class="main-container">
		<div class="main-container__content">
			<div class="place__info">
				<div class="place__info--img">
					<img src="/img/place/${restaurant.plImgPath}" alt="이미지 준비중"
						class="place--img" />
				</div>
				<div class="place__info--text">
					<ul class="place__info--text--box">
						<li class="placeName">${restaurant.placeName}</li>
						<li class="info">Address : ${restaurant.plAddress}</li>
						<li class="info">Phone : ${restaurant.plPhone}</li>
						<li class="info"><a href="${restaurant.plWebsite}"
							target="_blank" class="place--webSite">Web :
								${restaurant.plWebsite}</a></li>
					</ul>
				</div>
				<!-- areaNo, placeNo, userNo => parameter -->
				<a href="${pageContext.request.contextPath}/reservation/general?area=${area.areaNo}&place=${place.placeNo}&user=${user.userNo}" class="info__reservation">예약하러 가기</a>
			</div>
			</div>
		</div>
	

	<!-- 댓글 섹션 -->
	<div class="comment-section">
		<h2>댓글</h2>

		<!-- 댓글 입력 폼 -->
		<div class="comment-form">
			<form action="${pageContext.request.contextPath}/" method="post">
				<input type="hidden" name="placeNo" value="${cafe.placeNo}" />
				<textarea class="commentContents" placeholder="댓글을 입력하시오" required></textarea>
				<button class="comment-upload" type="submit">댓글 등록하기</button>
			</form>
		</div>

		<!-- 댓글 목록 -->
		<div class="comment-list">
			<c:forEach var="comment" items="${commentList}">
				<div class="comment-item">
					<p>
						<strong>User ${comment.userNo}</strong> <span class="comment-date">${comment.commentCreDate}</span>
					</p>
					<p>${comment.commentContents}</p>
				</div>
			</c:forEach>
		</div>
	</div>
	<jsp:include page="/jsp/common/footer.jsp" />
</body>
</html>