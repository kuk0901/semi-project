<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>새 게시글 작성</title>
<!-- <link rel="stylesheet" type="text/css"  -->
<link rel="stylesheet"
  href="https://cdn.jsdelivr.net/npm/reset-css@5.0.2/reset.min.css">
<link rel="stylesheet" type="text/css"
  href="${pageContext.request.contextPath}/css/common/common.css">
<script defer
  src="${pageContext.request.contextPath}/js/movePage/movePageFncs.js"></script>
<style>
body {
  font-family: Arial, sans-serif;
  margin: 0;
  padding: 20px;
  background-color: #f9f9f9;
}

h2 {
  color: #333;
}

form {
  background-color: #fff;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  max-width: 600px;
  margin: auto;
}

label {
  display: block;
  margin-bottom: 8px;
  font-weight: bold;
}

input[type="text"], textarea, input[type="file"] {
  width: 100%;
  padding: 8px;
  margin-bottom: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

input[type="submit"] {
  background-color: #4CAF50;
  color: white;
  padding: 10px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

input[type="submit"]:hover {
  background-color: #45a049;
}
</style>
</head>
<body>
  <jsp:include page="/jsp/common/header.jsp" />
  <div id="main-container">


    <div id="main-container">
      <h2>Add New Notice</h2>
      <form action="${pageContext.request.contextPath}/board/notice/add"
        method="post" enctype="multipart/form-data">
        <label for="subject">제목:</label> <input type="text" id="subject"
          name="subject" required> <label for="contentText">내용:</label>
        <textarea id="contentText" name="contentText" rows="10" required></textarea>
        <!-- 	첨부파일기능구현할것 -->
        <label for="file">파일: Only .png .jpeg .jpg .gif .webp
          files are allowed</label> <input type="file" id="file"
          name="contentFile"
          accept="image/jpeg, image/jpg, image/png, image/gif, image/webp"
          multiple onchange="handleFileSelect(event);">
        <div id="file-list"></div>

        <input type="submit" value="Submit">
      </form>

    </div>
    <a href="<%=request.getContextPath()%>/board/notice/list">목록으로
      돌아가기</a>
  </div>
  <jsp:include page="/jsp/common/footer.jsp" />
</body>


<!-- 자바스크립트 파일 로드 -->



</html>
