<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록</title>
<!-- CSS 파일 연결 -->
<link rel="stylesheet"
  href="https://cdn.jsdelivr.net/npm/reset-css@5.0.2/reset.min.css">
<link rel="stylesheet" type="text/css"
  href="${pageContext.request.contextPath}/css/common/common.css">
<link rel="stylesheet" type="text/css"
  href="${pageContext.request.contextPath}/css/board/freeboard/freeBoardList.css">
<script defer src="${pageContext.request.contextPath}/js/movePage/movePageFncs.js"></script>
</head>
<script type="text/javascript">
    var contextPath = "<%=request.getContextPath()%>
  ";
</script>
<script defer
  src="${pageContext.request.contextPath}/js/board/freeboard/freeBoardList.js"></script>
<body>
  <jsp:include page="/jsp/common/header.jsp" />
  <div id="main-container">
    <h2 class="title">자유게시판</h2>
    <hr />

    <table class="board-table">
      <thead>
        <tr>
          <th class="table-header">번호</th>
          <th class="table-header">제목</th>
          <th class="table-header">내용</th>
       
       
          <th class="table-header">작성일</th>
          <th class="table-header">수정일</th>
          <th class="table-header">사용자 번호</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="board" items="${boardList}">
          <tr class="table-row">
            <td class="table-cell"><c:out
                value="${board.contentNo}" /></td>
            <td class="table-cell"><a
              href="${pageContext.request.contextPath}/board/freeboard/detail?contentNo=${board.contentNo}"
              class="table-link"> <c:out
                  value="${board.contentSubject}" />
            </a></td>
            <td class="table-cell"><c:out
                value="${board.contentText}" /></td>
           
           
            <td class="table-cell"><c:out
                value="${board.contentCreDate}" /></td>
            <td class="table-cell"><c:out
                value="${board.contentUpdateDate}" /></td>
            <td class="table-cell"><c:out value="${board.userNo}" /></td>
          </tr>
        </c:forEach>
      </tbody>
    </table>

    <div class="pagination">
      <c:if test="${totalPages > 1}">
        <c:forEach var="i" begin="1" end="${totalPages}">
          <c:choose>
            <c:when test="${i == currentPage}">
              <strong class="current-page">${i}</strong>
            </c:when>
            <c:otherwise>
              <a
                href="${pageContext.request.contextPath}/board/freeboard/list?page=${i}"
                class="pagination-link">${i}</a>
            </c:otherwise>
          </c:choose>
        </c:forEach>
      </c:if>
    </div>

    <div class="button-container">
     
      <button
        onclick="location.href='<%=request.getContextPath()%>/board/freeboard/add'"
        class="action-button">새 글 작성</button>
    </div>
  </div>

  <jsp:include page="/jsp/common/footer.jsp" />
</body>
</html>
