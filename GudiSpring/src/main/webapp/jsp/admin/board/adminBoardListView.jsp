<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
 <link rel="stylesheet"
   href="https://cdn.jsdelivr.net/npm/reset-css@5.0.2/reset.min.css">
   <link rel="stylesheet" type="text/css" 
    href="${pageContext.request.contextPath}/css/common/common.css">
    <link rel="stylesheet" type="text/css" 
    href="${pageContext.request.contextPath}/css/admin/board/adminBoardList.css">
    <link rel="stylesheet"
  href="${pageContext.request.contextPath}/css/common/admin.css" />
<link rel="stylesheet"
  href="${pageContext.request.contextPath}/css/admin/nav.css" />
  <script defer type="text/javascript"
  src="${pageContext.request.contextPath}/js/movePage/movePageFncs.js"></script>
<script defer type="text/javascript"
  src="${pageContext.request.contextPath}/js/admin/navFocus.js"></script>
</head>
<body>

 <jsp:include page="/jsp/admin/nav.jsp"></jsp:include>
<div id="main-container">
<table id="boardTable">
    <thead>
        <tr>
            <th class="boardHeader">게시판</th>
            <th class="boardHeader">제목</th>
            <th class="boardHeader">작성자</th>
            <th class="boardHeader">작성일</th>
            <th class="boardHeader">수정일</th>
             <th class="boardHeader">삭제</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="board" items="${totalBoardList}">
            <tr>
                <td class="boardCell">${board.boardInfoName}</td> <!-- 게시판 이름 -->
                 <td class="boardCell">
                    <a href="${pageContext.request.contextPath}/admin/board/detail?contentNo=${board.contentNo}">
                        ${board.contentSubject}
                    </a>
                </td>
                <td class="boardCell">${board.nickname}</td>
                <td class="boardCell">${board.contentCreDate}</td>
                <td class="boardCell">${board.contentUpdateDate}</td>
                  <td class="boardCell">
                  <form action="${pageContext.request.contextPath}/admin/board/delete" method="post">
                        <input type="hidden" name="contentNo" value="${board.contentNo}">
                        <button name="sumbit" type="submit" onclick="return confirm('정말로 삭제하시겠습니까?')">삭제</button>
                    </form>
                    </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<div class="pagination">
    <c:forEach var="i" begin="1" end="${totalPages}">
        <a href="?page=${i}" class="<c:if test='${i == currentPage}'>active</c:if>">${i}</a>
    </c:forEach>
</div>
</div>

</body>
</html>