<%@ page import="gudiSpring.freeboard.dto.BoardDto"%>
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

</head>
<script type="text/javascript">var contextPath = "<%=request.getContextPath()%>";

</script>
<script defer
  src="${pageContext.request.contextPath}/js/movePage/movePageFncs.js"></script>
<script defer
  src="${pageContext.request.contextPath}/js/board/freeboard/freeBoardDetail.js"></script>
<body>
  <jsp:include page="/jsp/common/header.jsp" />

  <!-- main -->
  <div id="main-container">
    <h2>게시글 상세 조회</h2>
    <!-- 게시글 정보 출력 -->
    <table>
      <%
      BoardDto boardDto = (BoardDto) request.getAttribute("boardDto");
      if (boardDto != null) {
      %>
      <tr>
        <th>번호</th>
        <td><%=boardDto.getContentNo()%></td>
      </tr>
      <tr>
        <th>제목</th>
        <td><%=boardDto.getContentSubject()%></td>
      </tr>
      <tr>
        <th>내용</th>
        <td><%=boardDto.getContentText()%>
      </tr>
      <tr>
        <th>파일</th>
        <td><%=boardDto.getContentFile()%></td>
      </tr>

      <tr>
        <th>작성일</th>
        <td><%=boardDto.getContentCreDate()%></td>
      </tr>
      <tr>
        <th>수정일</th>
        <td><%=boardDto.getContentUpdateDate()%></td>
      </tr>
      <tr>
        <th>작성자</th>
        <td><%=boardDto.getUserNo()%></td>
      </tr>
    </table>
    <!-- 게시글 수정 버튼 추가 -->
    <button
      onclick="location.href='<%=request.getContextPath()%>/board/freeboard/edit?contentNo=<%=boardDto.getContentNo()%>'">수정</button>
    <!-- 게시글 삭제 버튼 추가 -->
    <button onclick="confirmDeletePost(<%=boardDto.getContentNo()%>)">게시글
      삭제</button>
    <h3>댓글 달기</h3>
    <form action="<%=request.getContextPath()%>/addComment"
      method="post" onsubmit="return validateForm(this);">
      <input type="hidden" name="contentNo"
        value="<%=boardDto.getContentNo()%>"> <input
        type="hidden" name="boardType" value="freeboard">
      <!-- 게시판 유형 -->
      <textarea name="commentContent" rows="4" cols="50"
        placeholder="댓글을 입력하세요"></textarea>
      <br> <input type="submit" value="댓글 추가">
    </form>
    <h3>댓글</h3>
    <ul class="comments">
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
            onclick="confirmDelete(${comment.commentNo}, <%= boardDto.getContentNo() %>, 'freeboard')">삭제</button>
          <button onclick="openEditForm(${comment.commentNo})">수정</button>
          <!-- 수정 폼 -->
          <div id="editForm-${comment.commentNo}" class="edit-form">
            <form action="<%=request.getContextPath()%>/editComment"
              method="post" onsubmit="return validateForm(this);">
              <input type="hidden" name="commentNo"
                value="${comment.commentNo}"> <input
                type="hidden" name="contentNo"
                value="<%=boardDto.getContentNo()%>"> <input
                type="hidden" name="boardType" value="freeboard">
              <textarea name="commentContent" rows="3">${comment.contentComment}</textarea>
              <input type="submit" value="수정 완료">
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
