<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 반려견과 어울리는 장소는?</title>
<link rel="stylesheet"
  href="https://cdn.jsdelivr.net/npm/reset-css@5.0.2/reset.min.css">
<link rel="stylesheet"
  href="${pageContext.request.contextPath}/css/common/common.css"
  type="text/css" />
<link rel="stylesheet"
  href="${pageContext.request.contextPath}/css/survey/placeMbtiTest/questions.css"
  type="text/css" />
<script defer src="${pageContext.request.contextPath}/js/movePage/movePageFncs.js"></script>
<script type="module" defer src="${pageContext.request.contextPath}/js/survey/placeMbtiTest/questions.js"></script>
</head>
<body>
<jsp:include page="/jsp/common/header.jsp" />

<div id="main-container">
  <div class="main-container__content">
    <div class="progress">
      <div class="value"></div>
    </div>
    
    <div class="question-box">
      <div class="number"></div>
      <div class="question"></div>
      <div class="btn btn-gray choice choice1"></div>
      <div class="btn btn-gray choice choice2"></div>
    </div>
  </div> <!-- main-container__content -->
</div> <!-- main-container -->

<jsp:include page="/jsp/common/footer.jsp" />
</body>
</html>