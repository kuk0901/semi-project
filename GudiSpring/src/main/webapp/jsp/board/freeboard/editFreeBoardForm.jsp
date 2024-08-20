<%@ page import="gudiSpring.board.dto.freeboard.BoardDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정</title>
<!-- CSS 파일 연결 -->
<link rel="stylesheet"
  href="https://cdn.jsdelivr.net/npm/reset-css@5.0.2/reset.min.css">
<link rel="stylesheet" type="text/css"
  href="${pageContext.request.contextPath}/css/common/common.css">
<link rel="stylesheet" type="text/css"
  href="${pageContext.request.contextPath}/css/board/freeboard/editFreeBoard.css">
<script defer src="${pageContext.request.contextPath}/js/movePage/movePageFncs.js"></script>
<script defer
  src="${pageContext.request.contextPath}/js/board/freeboard/editFreeBoard.js"></script>
</head>
<body>
  <jsp:include page="/jsp/common/header.jsp" />
  <div id="main-container">
    <h2 class="title">게시글 수정</h2>
    <form action="<%=request.getContextPath()%>/board/freeboard/edit"
      method="post" enctype="multipart/form-data" class="edit-form">
      <input type="hidden" name="contentNo"
        value="${boardDto.contentNo}"> <label for="subject"
        class="form-label">제목:</label> <input type="text" id="subject"
        name="contentSubject" value="${boardDto.contentSubject}"
        class="form-input" required> <label for="text"
        class="form-label">내용:</label>
      <textarea id="text" name="contentText" rows="5"
        class="form-textarea" required>${boardDto.contentText}</textarea>

      <label for="file" class="form-label">첨부 파일: .png .jpeg
        .jpg .gif .webp만 업로드 가능합니다</label> <input type="file" id="file"
        name="contentFile" class="form-file-input"
        accept="image/jpeg, image/jpg, image/png, image/gif, image/webp"><br>

      <c:if test="${not empty boardDto.contentFile}">
        <label class="delete-file"> <input type="checkbox"
          id="deleteFile" name="deleteFile" value="true"
          class="delete-checkbox"> 기존 파일 삭제
        </label>
      </c:if>
      <input type="submit" value="수정 완료" class="submit-button">
    </form>
    <a href="<%=request.getContextPath()%>/board/freeboard/list"
      class="back-link">목록으로 돌아가기</a>
  </div>

  <jsp:include page="/jsp/common/footer.jsp" />
</body>
</html>
