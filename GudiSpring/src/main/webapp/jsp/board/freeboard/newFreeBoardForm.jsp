<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>새 게시글 작성</title>
<link rel="stylesheet"
  href="https://cdn.jsdelivr.net/npm/reset-css@5.0.2/reset.min.css">
<link rel="stylesheet" type="text/css"
  href="${pageContext.request.contextPath}/css/common/common.css">
<link rel="stylesheet" type="text/css"
  href="${pageContext.request.contextPath}/css/board/freeboard/newFreeBoard.css">
<script defer src="${pageContext.request.contextPath}/js/movePage/movePageFncs.js"></script>
<script defer
  src="${pageContext.request.contextPath}/js/board/freeboard/newFreeBoard.js"></script>
</head>
<body>
  <jsp:include page="/jsp/common/header.jsp" />
  <div id="main-container">
    <h2 class="title">새 게시글 작성</h2>

    <form action="<%=request.getContextPath()%>/board/freeboard/add"
      method="post" enctype="multipart/form-data" id="post-form">
      <label for="subject" class="form-label">제목:</label> <input
        type="text" id="subject" name="contentSubject"
        class="form-input" required> <label for="text"
        class="form-label">내용:</label>
      <textarea id="text" name="contentText" rows="5"
        class="form-textarea" required></textarea>

      <label for="file" class="form-label">첨부 파일: .png .jpeg
        .jpg .gif .webp만 업로드 가능합니다</label> <input type="file" id="file"
        name="contentFile" class="form-file-input"
        accept="image/jpeg, image/jpg, image/png, image/gif, image/webp"><br>

      <input type="submit" value="작성 완료" class="submit-button">
    </form>
    <a href="<%=request.getContextPath()%>/board/freeboard/list"
      class="back-link">목록으로 돌아가기</a>
  </div>

  <jsp:include page="/jsp/common/footer.jsp" />

</body>
</html>
