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
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/common/common.css"
	type="text/css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/reservation/Reservation.css"
	type="text/css" />
<script defer src="${pageContext.request.contextPath}/js/movePage/movePageFncs.js"></script>
<style type="text/css">
</style>
<script type="text/javascript">
	
</script>
</head>
<body>
	<jsp:include page="/jsp/common/header.jsp" />

	<div id="main-container">

		<div class="main-container__content">

			<!--정보 입력 -->
			<div class="reservation-maincontainer__content">
				<form class="" action="./general" method="post" onsubmit="return confirm('정말 예약하시겠습니까?');">
					<div class="reservation-maincontainer__content__element">
						<div class="visit-date">
							<label for="visitDate">방문일</label>
							<div class="visitDate_box">
								<input id="visitDate" type="datetime-local"
									value="${reservationDto.visitDate}" name="visitDate">
							</div>
						</div>
					</div>

					<div class="reservation-maincontainer__content__element">
						<div class="visitor-num">
							<label for="visitorNum">방문객수</label>
							<div class="visitorNum_box">
								<input id="visitorNum" type="number" min="1" max="9"
									value="${reservationDto.visitorNum}" name="visitorNum">
							</div>
						</div>
					</div>

					<div class="reservation-maincontainer__content__element__submit">
						<input type="submit" class="reserv__btn" value="예약하기" />
					</div>
				</form>
			</div>
			<!-- reservation-maincontainer__content -->
		</div>
	</div>

	<jsp:include page="/jsp/common/footer.jsp" />
</body>
</html>