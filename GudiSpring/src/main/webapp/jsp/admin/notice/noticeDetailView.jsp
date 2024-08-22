<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>공지사항 상세보기</title>
    
	    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reset-css@5.0.2/reset.min.css">
     <link rel="stylesheet" type="text/css" 
    href="${pageContext.request.contextPath}/css/common/common.css"> 
    <link rel="stylesheet"
  href="${pageContext.request.contextPath}/css/admin/nav.css" />
    <link rel="stylesheet" type="text/css" 
    href="${pageContext.request.contextPath}/css/admin/notice/noticeBoardDetail.css">
       <link rel="stylesheet"
  href="${pageContext.request.contextPath}/css/common/admin.css" />

   <script defer type="text/javascript"
  src="${pageContext.request.contextPath}/js/movePage/movePageFncs.js"></script>
<script defer type="text/javascript"
  src="${pageContext.request.contextPath}/js/admin/navFocus.js"></script>
</head>
<body>
  <jsp:include page="/jsp/admin/nav.jsp"></jsp:include>
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
                <td class="value" id="contentAuthor">${noticeDto.nickname}</td>
            </tr>
			 </table>
			 <table id="contentText-table">
            <!-- 내용은 별도의 행으로 크게 표시 -->
            <tr>
                
                <td class="value" id="contentText">${noticeDto.contentText}</td>
            </tr>
        	</table>
    <div class="buttons">
       <!-- 목록으로 돌아가기 버튼 -->
            <a href="${pageContext.request.contextPath}/admin/board/list">목록으로 돌아가기</a>
            
            <!-- 수정 버튼 -->
            <a href="${pageContext.request.contextPath}/admin/notice/edit?contentNo=${noticeDto.contentNo}">수정</a>
            
            <!-- 삭제 버튼 -->
            <a href="${pageContext.request.contextPath}/admin/notice/delete?contentNo=${noticeDto.contentNo}" class="delete-btn">삭제</a>
        </div>
    </div>
   
  
</body>
</html>
