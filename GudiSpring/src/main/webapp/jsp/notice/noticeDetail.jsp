<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>공지사항 상세보기</title>
    <link rel="stylesheet" type="text/css" 
    href="${pageContext.request.contextPath}/css/board/noticeboard/noticeBoardList.css">
       <link rel="stylesheet" type="text/css" 
    href="${pageContext.request.contextPath}/css/common/common.css"> 
    <style>
       
    </style>
</head>
<body>
 <jsp:include page="/jsp/common/header.jsp"/>
 <div id="main-container">
    <h1>공지사항 상세보기</h1>
    <table>
        <tr>
            <th>번호</th>
            <td>${notice.contentNo}</td>
        </tr>
        <tr>
            <th>제목</th>
            <td>${notice.contentSubject}</td>
        </tr>
        <tr>
            <th>내용</th>
            <td>${notice.contentText}</td>
        </tr>
        <tr>
            <th>파일</th>
            <td>${notice.contentFile}</td>
        </tr>
        <tr>
            <th>게시판 정보 번호</th>
            <td>${notice.contentBoardInfoNo}</td>
        </tr>
        <tr>
            <th>작성일</th>
            <td>${notice.contentCreDate}</td>
        </tr>
        <tr>
            <th>수정일</th>
            <td>${notice.contentUpdateDate}</td>
        </tr>
        <tr>
            <th>작성자</th>
            <td>관리자</td>
        </tr>
    </table>
    <div class="buttons">
        <a href="notices">목록으로 돌아가기</a>
        <a href="/test/board/freeboard/list">자유게시판으로 가기</a>
    </div>
    </div>
    <jsp:include page="/jsp/common/footer.jsp"/>
</body>
</html>
