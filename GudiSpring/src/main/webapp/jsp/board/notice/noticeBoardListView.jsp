<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="EUC-KR">
    <title>공지사항 게시판</title>
 	  <link rel="stylesheet"
  href="https://cdn.jsdelivr.net/npm/reset-css@5.0.2/reset.min.css">
       <link rel="stylesheet" type="text/css" 
    href="${pageContext.request.contextPath}/css/common/common.css"> 
     <link rel="stylesheet" type="text/css" 
    href="${pageContext.request.contextPath}/css/board/noticeboard/noticeBoardList.css">
    <script defer
  src="${pageContext.request.contextPath}/js/movePage/movePageFncs.js"></script>

    
  
</head>
<body>

    <jsp:include page="/jsp/common/header.jsp" />

    <div id="main-container">
        <h1 id="page-title">공지사항 목록</h1>
        <table id="boardListTable" class="table">
            <thead>
                <tr class="table-header">
                    <th class="table-header-item">번호</th>
                    <th class="table-header-item">제목</th>
                    <th class="table-header-item">내용</th>
                    <th class="table-header-item">작성자</th>
                    <th class="table-header-item">작성일</th>
                    <th class="table-header-item">수정일</th>
                </tr>
            </thead>
            <tbody id="board-list-body">
                <c:forEach var="notice" items="${noticeList}">
                    <tr class="table-row">
                        <td class="table-item">${notice.contentNo}</td>
                        <td class="table-item abtn"><a href="${pageContext.request.contextPath}/board/notice/detail?contentNo=${notice.contentNo}" class="content-link">${notice.contentSubject}</a></td>
                        <td class="table-item">${notice.contentText}</td>
                        <td class="table-item">${notice.nickname}</td>
                        <td class="table-item">${notice.contentCreDate}</td>
                        <td class="table-item">${notice.contentUpdateDate}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>   
    </div>
    
    <jsp:include page="/jsp/common/footer.jsp" />

</body>
</html>
