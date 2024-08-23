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
		<ul class="reservation__finish">
			<li class="finish__ment">예약이 완료되었습니다</li>
			<li class="main__move">
				<a href="${pageContext.request.contextPath}">처음으로 돌아가기</a>		
			</li>	
		</ul>					
		</div>
	</div>

	<jsp:include page="/jsp/common/footer.jsp" />
</body>
</html>