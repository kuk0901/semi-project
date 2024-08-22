<%@ page import="gudiSpring.board.dto.reviewboard.ReviewBoardDto" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 수정</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reset-css@5.0.2/reset.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common/common.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/board/reviewboard/editReviewBoard.css">
   
    <!-- jQuery 라이브러리 포함 -->
   	  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>	
    <script> var contextPath = "<%= request.getContextPath() %>"; 
    var filePaths = [
        <c:forEach var="file" items="${boardDto.contentFiles}">
            "${file}"<c:if test="${!fn:endsWith(file, ',')}">,</c:if>
        </c:forEach>
    ];</script>
    <script defer
  src="${pageContext.request.contextPath}/js/movePage/movePageFncs.js"></script>
    <script defer src="${pageContext.request.contextPath}/js/board/reviewboard/editReviewBoard.js"></script>
</head>
<body id="body-style">
<jsp:include page="/jsp/common/header.jsp" />
<div id="main-container">
    <h2>게시글 수정</h2>
    <form id="editForm" action="<%= request.getContextPath() %>/board/reviewboard/edit" method="post" enctype="multipart/form-data">
        <input type="hidden" name="contentNo" value="${boardDto.contentNo}">
          <input type="hidden" id="hiddenContentText" name="contentText" value="">
        
        <label for="subject">제목:</label>
        <input type="text" id="subject" name="contentSubject" value="${boardDto.contentSubject}" required>

        <label for="contenttext">내용:</label>
        <div id="contentText" contenteditable="true" class="textarea-field">${boardDto.contentText}</div>
		
		<div id="file-selection-container">
        <label for="file">첨부 파일: .png, .jpeg, .jpg, .gif, .webp만 업로드 가능합니다</label>
        <input type="file" id="file" 
        name="contentFile" accept="image/jpeg, image/jpg, image/png, image/gif, image/webp"  multiple ><br>
		</div>
       
     
          <!-- 새로운 파일 미리보기 리스트 -->
          
        <div id="new-file-list"></div>
        <div class="button-container">
		   <button type="button" onclick="insertImageFromInput()" class="submit-button">이미지 본문삽입</button>
		  
		</div>
        <input type="submit" id="completeButton" value="수정 완료">
    </form>
      <!-- 기존 파일 리스트 -->
		<c:if test="${not empty boardDto.contentFiles}">
		    <div id="existing-file-list">
		        <p>기존 파일:</p>
		        <c:forEach var="file" items="${boardDto.contentFiles}">
		            <div id="file-${file}" class="delete-form-container">
		                <span class="file-name">${file}</span>
		                <form class="delete-form" action="${pageContext.request.contextPath}/board/reviewboard/deleteFile" method="post">
		                    <input type="hidden" name="contentNo" value="${boardDto.contentNo}">
		                    <input type="hidden" name="fileName" value="${file}">
		                    <button type="submit" class="delete-button">삭제</button>
		                </form>
		            </div>
		        </c:forEach>
		    </div>
		</c:if>
    <a href="<%= request.getContextPath() %>/board/reviewboard/list" class="back-link">목록으로 돌아가기</a>
</div>
 <jsp:include page="/jsp/common/footer.jsp"/> 
</body>
</html>
