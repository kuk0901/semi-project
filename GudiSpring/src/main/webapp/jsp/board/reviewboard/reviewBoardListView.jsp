<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록</title>
<link rel="stylesheet"
  href="https://cdn.jsdelivr.net/npm/reset-css@5.0.2/reset.min.css">
<link rel="stylesheet" type="text/css"
  href="${pageContext.request.contextPath}/css/common/common.css">
<link rel="stylesheet" type="text/css"
  href="${pageContext.request.contextPath}/css/board/reviewboard/reviewBoardList.css">
<script defer
  src="${pageContext.request.contextPath}/js/movePage/movePageFncs.js"></script>
<script defer
  src="${pageContext.request.contextPath}/js/board/reviewboard/reviewBoardList.js"></script>
</head>
<body>
  <jsp:include page="/jsp/common/header.jsp" />
  
  <!-- main -->
  <div id="main-container">
    <h1>게시판 목록</h1>
    <table>
      <thead>
        <tr>
          <th>번호</th>
          <th>제목</th>
          <th>내용</th>
          <th>첨부파일</th>
          <th>게시판 정보 번호</th>
          <th>작성일</th>
          <th>수정일</th>
          <th>사용자 번호</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="board" items="${boardList}">
          <tr>
            <td><c:out value="${board.contentNo}" /></td>
            <td><a
              href="/test/board/reviewboard/detail?contentNo=${board.contentNo}">
                <c:out value="${board.contentSubject}" />
            </a></td>
            <td><c:out value="${board.contentText}" /></td>
            <td><c:out value="${board.contentFiles}" /></td>
            <td><c:out value="${board.contentBoardInfoNo}" /></td>
            <td><c:out value="${board.contentCreDate}" /></td>
            <td><c:out value="${board.contentUpdateDate}" /></td>
            <td><c:out value="${board.userNo}" /></td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
    <!-- 페이징 네비게이션 -->
    <c:if test="${totalPages > 1}">
      <div class="pagination">
        <c:forEach var="i" begin="1" end="${totalPages}">
          <c:choose>
            <c:when test="${i == currentPage}">
              <strong>${i}</strong>
            </c:when>
            <c:otherwise>
              <a
                href="${pageContext.request.contextPath}/board/reviewboard/list?page=${i}">${i}</a>

            </c:otherwise>
          </c:choose>
        </c:forEach>
      </div>
    </c:if>

    <div class="button-container">
      <button onclick="goToNotice()">임시공지사항</button>

      <button
        onclick="location.href='<%=request.getContextPath()%>/board/freeboard/list'">자유
        게시판으로 이동</button>
      <button
        onclick="location.href='<%=request.getContextPath()%>/board/reviewboard/add'">새
        글 작성</button>
    </div> <!-- main -->
    <jsp:include page="/jsp/common/footer.jsp" />
  </div>
</body>
</html>
