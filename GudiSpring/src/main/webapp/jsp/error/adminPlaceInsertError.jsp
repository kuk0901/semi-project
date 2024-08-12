<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>시스템 에러</title>
<link
  href="https://cdn.jsdelivr.net/npm/reset-css@5.0.2/reset.min.css"
  rel="stylesheet" />
<link rel="stylesheet"
  href="${pageContext.request.contextPath}/css/common/common.css" />
<link rel="stylesheet"
  href="${pageContext.request.contextPath}/css/common/error.css" />
<link rel="stylesheet"
  href="${pageContext.request.contextPath}/css/common/admin.css" />
<link rel="stylesheet"
  href="${pageContext.request.contextPath}/css/admin/nav.css" />
</head>
<body>

  <jsp:include page="/jsp/admin/nav.jsp" />

  <div id="main-container">
    <h1 class="error-msg">
      Sorry..<br />
      Internal Server Error
    </h1>
  </div>

</body>
</html>