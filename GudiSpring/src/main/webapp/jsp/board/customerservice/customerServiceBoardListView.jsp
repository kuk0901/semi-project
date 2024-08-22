<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="gudiSpring.board.dto.reviewboard.ReviewBoardDto" %>
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
    href="${pageContext.request.contextPath}/css/board/customerservice/customerServiceBoardList.css">
    
    <style>
        
    </style>
      <script defer src="${pageContext.request.contextPath}/js/movePage/movePageFncs.js"></script>

    <script defer src="${pageContext.request.contextPath}/js/board/customerservice/customerServiceBoardList.js"></script>
</head>
<body id="bodystyle">
<jsp:include page="/jsp/common/header.jsp"  />
<div id="main-container">
    <h1 id="title">게시판 목록</h1>
    <table id="boardListTable">
        <thead>
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>내용</th>
                <th>게시판이름</th>
                <th>작성일</th>
                <th>수정일</th>
                <th>닉네임</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="board" items="${boardList}">
                <tr>
                    <td><c:out value="${board.contentNo}" /></td>
                    <td><a href="${pageContext.request.contextPath}/board/customerserviceboard/detail?contentNo=${board.contentNo}">
                        <c:out value="${board.contentSubject}" /></a>
                    </td>
                     <td>
                      <c:out value="${board.contentText}" />      
                     </td>
                    <td><c:out value="${board.boardInfoName}" /></td>

                    <td><c:out value="${board.contentCreDate}" /></td>
                    <td><c:out value="${board.contentUpdateDate}" /></td>
                   	  <td><c:out value="${board.nickname}" /></td> <!-- 여기에서 닉네임 출력 -->

                </tr>
            </c:forEach>
        </tbody>
    </table>
      <!-- 페이징 네비게이션 -->
   
<div class="pagination">
    <c:forEach var="i" begin="1" end="${totalPages}">
        <c:choose>
            <c:when test="${i == currentPage}">
                <strong id="page-${i}" class="pagination-link active">${i}</strong>
            </c:when>
            <c:otherwise>
                <a href="${pageContext.request.contextPath}/board/customerservice/list?page=${i}" id="page-${i}" class="pagination-link">${i}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>
</div>
    
    <div class="button-container">
        <button onclick="location.href='<%=request.getContextPath()%>/board/freeboard/list'">임시공지사항</button>
       
		<button onclick="location.href='<%=request.getContextPath()%>/board/freeboard/list'">자유 게시판으로 이동</button>
        <button onclick="location.href='<%=request.getContextPath()%>/board/customerserviceboard/add'">새 글 작성</button>
    </div>
    <jsp:include page="/jsp/common/footer.jsp"/>
</div>
</body>
</html>
