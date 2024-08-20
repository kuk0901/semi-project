<%@ page import="gudiSpring.board.dto.reviewboard.ReviewBoardDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정</title>
<link rel="stylesheet"
  href="https://cdn.jsdelivr.net/npm/reset-css@5.0.2/reset.min.css">
<link rel="stylesheet" type="text/css"
  href="${pageContext.request.contextPath}/css/common/common.css">
<link rel="stylesheet" type="text/css"
  href="${pageContext.request.contextPath}/css/board/reviewboard/editReviewBoard.css">
<script defer src="${pageContext.request.contextPath}/js/movePage/movePageFncs.js"></script>
<script>
    var contextPath = "<%=request.getContextPath()%>";
    </script>
<script defer
  src="${pageContext.request.contextPath}/js/board/reviewboard/editReviewBoard.js"></script>
</head>
<body>
  <jsp:include page="/jsp/common/header.jsp" />
  <div id="main-container">
    <h2>게시글 수정</h2>
    <form
      action="<%=request.getContextPath()%>/board/reviewboard/edit"
      method="post" enctype="multipart/form-data">
      <input type="hidden" name="contentNo"
        value="${boardDto.contentNo}"> <label for="subject">제목:</label>
      <input type="text" id="subject" name="contentSubject"
        value="${boardDto.contentSubject}" required> <label
        for="text">내용:</label>
      <textarea id="text" name="contentText" rows="5" required>${boardDto.contentText}</textarea>

      <label for="file">첨부 파일: .png, .jpeg, .jpg, .gif, .webp만
        업로드 가능합니다</label> <input type="file" id="file" name="contentFile"
        accept="image/jpeg, image/jpg, image/png, image/gif, image/webp"
        multiple><br>

      <!-- 기존 파일 리스트 -->
      <c:if test="${not empty boardDto.contentFiles}">
        <div class="file-list">
          <p>기존 파일:</p>
          <c:forEach var="file" items="${boardDto.contentFiles}">
            <div id="file-${file}" class="file-item">
              <span>${file}</span>
              <button type="button"
                onclick="deleteFile('${file}', ${boardDto.contentNo})">삭제</button>
            </div>
          </c:forEach>
        </div>
      </c:if>

      <input type="submit" value="수정 완료">
    </form>
    <a href="<%=request.getContextPath()%>/board/reviewboard/list">목록으로
      돌아가기</a>
  </div>
  <jsp:include page="/jsp/common/footer.jsp" />
</body>
</html>
