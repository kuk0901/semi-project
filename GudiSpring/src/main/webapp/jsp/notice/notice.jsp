<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="EUC-KR">
    <title>공지사항 게시판</title>
    <link rel="stylesheet" type="text/css" 
    href="${pageContext.request.contextPath}/css/board/noticeboard/noticeBoardList.css">
       <link rel="stylesheet" type="text/css" 
    href="${pageContext.request.contextPath}/css/common/common.css"> 
    <style>
        
    </style>
</head>
<body>
 <div id="main-container">
    <h1>공지사항 목록</h1>
    <table>
        <thead>
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>내용</th>
                <th>파일</th>
                <th>작성일</th>
                <th>수정일</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="notice" items="${notices}">
                <tr>
                    <td>${notice.contentNo}</td>
                    <td><a href="noticedetail?contentNo=${notice.contentNo}">${notice.contentSubject}</a></td>
                    <td>${notice.contentText}</td>
                    <td>${notice.contentFile}</td>
                    <td>${notice.contentCreDate}</td>
                    <td>${notice.contentUpdateDate}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <div class="back-link">
        <a href="/test/board/freeboard/list">자유게시판으로 가기</a>
    </div>
    </div>
    <jsp:include page="/jsp/common/footer.jsp"/>
</body>
</html>
