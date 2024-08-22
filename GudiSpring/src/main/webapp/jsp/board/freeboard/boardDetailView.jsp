<%@ page import="gudiSpring.board.dto.freeboard.BoardDto"%>
<%@ page import="gudiSpring.comment.dto.CommentDto"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세 조회</title>
<link rel="stylesheet"
  href="https://cdn.jsdelivr.net/npm/reset-css@5.0.2/reset.min.css">
<link rel="stylesheet" type="text/css"
  href="${pageContext.request.contextPath}/css/common/common.css">
<link rel="stylesheet" type="text/css"
  href="${pageContext.request.contextPath}/css/board/freeboard/freeBoardDetail.css">
<script defer src="${pageContext.request.contextPath}/js/movePage/movePageFncs.js"></script>
</head>
<script type="text/javascript">var contextPath = "<%=request.getContextPath()%>";

</script>
<script defer
  src="${pageContext.request.contextPath}/js/board/freeboard/freeBoardDetail.js"></script>
<body>
  <jsp:include page="/jsp/common/header.jsp" />

  <div id="main-container">
    <h2 class="title">게시글 상세 조회</h2>
    <!-- 게시글 정보 출력 -->
    <table class="detail-table">
      <%
      BoardDto boardDto = (BoardDto) request.getAttribute("boardDto");
      if (boardDto != null) {
      %>
      <tr>
        <th class="table-header">번호</th>
        <td class="table-cell"><%=boardDto.getContentNo()%></td>
      </tr>
      <tr>
        <th class="table-header">제목</th>
        <td class="table-cell"><%=boardDto.getContentSubject()%></td>
      </tr>
      <tr>
        <th class="table-header">내용</th>
        <td class="table-cell"><%=boardDto.getContentText()%></td>
      </tr>
     
      <tr>
        <th class="table-header">작성일</th>
        <td class="table-cell"><%=boardDto.getContentCreDate()%></td>
      </tr>
      <tr>
        <th class="table-header">수정일</th>
        <td class="table-cell"><%=boardDto.getContentUpdateDate()%></td>
      </tr>
      <tr>
        <th class="table-header">작성자</th>
        <td class="table-cell"><%=boardDto.getUserNo()%></td>
      </tr>
    </table>
    <!-- 게시글 수정 버튼 추가 -->
    <button
      onclick="location.href='<%=request.getContextPath()%>/board/freeboard/edit?contentNo=<%=boardDto.getContentNo()%>'"
      class="action-button">수정</button>
    <!-- 게시글 삭제 버튼 추가 -->
    <button onclick="confirmDeletePost(<%=boardDto.getContentNo()%>)"
      class="action-button">게시글 삭제</button>

    <h3 class="subtitle">댓글 달기</h3>
    <form action="<%=request.getContextPath()%>/addComment"
      method="post" onsubmit="return validateForm(this);"
      class="comment-form">
      <input type="hidden" name="contentNo"
        value="<%=boardDto.getContentNo()%>"> <input
        type="hidden" name="boardType" value="freeboard">
      <textarea name="commentContent" rows="4" placeholder="댓글을 입력하세요"
        class="comment-textarea"></textarea>
      <br> <input type="submit" value="댓글 추가" class="submit-button">
    </form>

    <h3 class="subtitle">댓글</h3>
    <ul class="comments" id="comment-list">
      <c:forEach var="comment" items="${commentList}">
        <li class="comment">
          <p>
            <strong>댓글 번호:</strong>
            <c:out value="${comment.commentNo}" />
          </p>
          <p>
            <strong>내용:</strong>
            <c:out value="${comment.contentComment}" />
          </p>
          <p>
            <strong>작성일:</strong>
            <c:out value="${comment.commentCreDate}" />
          </p>
          <button
            onclick="confirmDelete(${comment.commentNo}, <%= boardDto.getContentNo() %>, 'freeboard')"
            class="action-button">삭제</button>
          <button onclick="openEditForm(${comment.commentNo})"
            class="action-button">수정</button> <!-- 수정 폼 -->
          <div id="editForm-${comment.commentNo}" class="edit-form">
            <form action="<%=request.getContextPath()%>/editComment"
              method="post" onsubmit="return validateForm(this);">
              <input type="hidden" name="commentNo"
                value="${comment.commentNo}"> <input
                type="hidden" name="contentNo"
                value="<%=boardDto.getContentNo()%>"> <input
                type="hidden" name="boardType" value="freeboard">
              <textarea name="commentContent" rows="3"
                class="edit-textarea">${comment.contentComment}</textarea>
              <input type="submit" value="수정 완료" class="submit-button">
            </form>
          </div>
        </li>
      </c:forEach>
    </ul>

    <%
    }
    %>
    <a href="<%=request.getContextPath()%>/board/freeboard/list"
      class="back-link">목록으로 돌아가기</a>
  </div>
  <jsp:include page="/jsp/common/footer.jsp" />
</body>
</html>
