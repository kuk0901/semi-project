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
</head>
<body>

  <jsp:include page="/jsp/common/header.jsp" />

  <div id="main-container">
    <h1>
      You do not have sufficient permissions<br /> to access this page.
    </h1>
  </div>

</body>
</html>