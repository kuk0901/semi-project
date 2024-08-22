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
    <script defer
  src="${pageContext.request.contextPath}/js/movePage/movePageFncs.js"></script>
    <style>
      
    </style>
</head>
<body>
 <jsp:include page="/jsp/common/header.jsp"/>
 <div id="main-container">
    <h1>공지사항 상세보기</h1>
    <table class="notice-table">
            <!-- 제목, 번호, 작성일, 수정일, 작성자를 한 줄에 배치 -->
            <tr class="info-row">
                <th class="label">제목</th>
                <td class="value" id="contentSubject">${noticeDto.contentSubject}</td>

                <th class="label">번호</th>
                <td class="value" id="contentNo">${noticeDto.contentNo}</td>

                <th class="label">작성일</th>
                <td class="value" id="contentCreDate">${noticeDto.contentCreDate}</td>

                <th class="label">수정일</th>
                <td class="value" id="contentUpdateDate">${noticeDto.contentUpdateDate}</td>

                <th class="label">작성자</th>
                <td class="value" id="contentAuthor">관리자</td>
            </tr>

            <!-- 내용은 별도의 행으로 크게 표시 -->
            <tr>
                <th class="label">내용</th>
                <td class="value" id="contentText" colspan="7">${noticeDto.contentText}</td>
            </tr>
        </table>
    <div class="buttons">
        <a href="${pageContext.request.contextPath}/board/notice/list">목록으로 돌아가기</a>
    </div>
    </div>
    <jsp:include page="/jsp/common/footer.jsp"/>
</body>
</html>
