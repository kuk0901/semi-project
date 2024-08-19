<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>시스템 오류</title>
<link href="
https://cdn.jsdelivr.net/npm/reset-css@5.0.2/reset.min.css
" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/error.css">
</head>
<body>
<jsp:include page="/jsp/common/errorHeader.jsp"/>
<div id="main-container">
  <h1>
    Sorry..<br />
    Internal Server Error
  </h1>
</div>

</body>
</html>