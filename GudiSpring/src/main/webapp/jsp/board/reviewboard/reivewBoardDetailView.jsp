<%@ page import="gudiSpring.reviewboard.dto.ReviewBoardDto"%>
<%@ page import="gudiSpring.comment.dto.CommentDto"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
  href="${pageContext.request.contextPath}/css/board/reviewboard/reviewBoardDetail.css">
<script defer
  src="${pageContext.request.contextPath}/js/movePage/movePageFncs.js"></script>
</head>
<body>
  <jsp:include page="/jsp/common/header.jsp" />

  <!-- main -->
  <div id="main-container">
    <h2>게시글 상세 조회</h2>

    <!-- 게시글 정보 출력 -->
    <table>
      <%
      ReviewBoardDto boardDto = (ReviewBoardDto) request.getAttribute("boardDto");
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
        <td><c:forEach var="file" items="${boardDto.contentFiles}">
            <!-- 'test' 부분을 제거한 경로로 이미지를 출력 -->
            <img src="${fn:replace(file, '/test', '')}"
              alt="Attached Image" />
          </c:forEach> ${boardDto.contentText}</td>
      </tr>

      <tr>
        <th>파일</th>
        <td><c:forEach var="file" items="${boardDto.contentFiles}">
            <p>
              <c:out value="${file}" />
            </p>
            <!-- 각 파일 경로를 출력 나중에지우시오! -->
          </c:forEach></td>
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
      onclick="location.href='<%=request.getContextPath()%>/board/reviewboard/edit?contentNo=<%=boardDto.getContentNo()%>'">수정</button>
    <!-- 게시글 삭제 버튼 추가 -->
    <button
      onclick="location.href='<%=request.getContextPath()%>/board/reviewboard/delete?contentNo=<%=boardDto.getContentNo()%>'">게시글
      삭제</button>

    <h3>댓글 달기</h3>
    <form action="<%=request.getContextPath()%>/addComment"
      method="post" onsubmit="return validateForm(this);">
      <input type="hidden" name="contentNo"
        value="<%=boardDto.getContentNo()%>"> <input
        type="hidden" name="boardType" value="reviewboard">
      <!-- 게시판 유형 value필수설정-->
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
          </p> <!-- 게시판 유형 필수설정-->
          <button
            onclick="confirmDelete(${comment.commentNo}, <%= boardDto.getContentNo() %>, 'reviewboard')">삭제</button>
          <button onclick="openEditForm(${comment.commentNo})">수정</button>
          <!-- 수정 폼 -->
          <div id="editForm-${comment.commentNo}" class="edit-form">
            <form action="<%=request.getContextPath()%>/editComment"
              method="post" onsubmit="return validateForm(this);">
              <input type="hidden" name="commentNo"
                value="${comment.commentNo}"> <input
                type="hidden" name="boardType" value="reviewboard">
              <!-- 게시판 유형 value필수설정-->
              <input type="hidden" name="contentNo"
                value="<%=boardDto.getContentNo()%>">
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
    <a href="<%=request.getContextPath()%>/reviewboardList"
      class="back-link">목록으로 돌아가기</a>
  </div>
  <!-- main -->

  <jsp:include page="/jsp/common/footer.jsp" />
</body>
</html>
