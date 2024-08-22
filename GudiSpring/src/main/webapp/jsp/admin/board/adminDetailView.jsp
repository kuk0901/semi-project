<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Detail View</title>
<link rel="stylesheet"
   href="https://cdn.jsdelivr.net/npm/reset-css@5.0.2/reset.min.css">
<link rel="stylesheet" type="text/css" 
    href="${pageContext.request.contextPath}/css/common/common.css">
<link rel="stylesheet" type="text/css" 
    href="${pageContext.request.contextPath}/css/admin/board/adminDetailView.css">
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
    <h1>게시글 상세보기</h1>
    <table id="detailTable">
        <tr>
            <th class="detailHeader">게시판</th>
            <td class="detailCell">${boardDetail.boardInfoName}</td>
        </tr>
        <tr>
            <th class="detailHeader">제목</th>
            <td class="detailCell">${boardDetail.contentSubject}</td>
        </tr>
        <tr>
            <th class="detailHeader">작성자</th>
            <td class="detailCell">${boardDetail.nickname}</td>
        </tr>
        <tr>
            <th class="detailHeader">작성일</th>
            <td class="detailCell">${boardDetail.contentCreDate}</td>
        </tr>
        <tr>
            <th class="detailHeader">수정일</th>
            <td class="detailCell">${boardDetail.contentUpdateDate}</td>
        </tr>
        <tr>
            <th class="detailHeader">내용</th>
            <td class="detailCell">${boardDetail.contentText}</td>
        </tr>
    </table>

    
     <!-- 댓글 리스트 추가 -->

    <ul id="comment-list" class="comments">
        <c:forEach var="comment" items="${commentList}">
            <li class="comment-item">
                <p><strong>작성자:</strong> ${comment.nickname}</p>
                <p><strong>내용:</strong> ${comment.contentComment}</p>
                <p><strong>작성일:</strong> ${comment.commentCreDate}</p>
                <!-- 삭제 버튼 추가 -->
                <form action="${pageContext.request.contextPath}/comment/delete" method="post">
                    <input type="hidden" name="commentNo" value="${comment.commentNo}">
                    <input type="hidden" name="contentNo" value="${boardDetail.contentNo}">
                    <button type="submit" class="btn delete-btn" onclick="return confirm('정말로 이 댓글을 삭제하시겠습니까?')">댓글 삭제</button>
                </form>
            </li>
        </c:forEach>
    </ul>

    <div id="admin-actions" class="actions">
        <a href="${pageContext.request.contextPath}/admin/board/list" class="btn view-btn">목록으로 돌아가기</a>
        <form action="${pageContext.request.contextPath}/admin/board/delete" method="post" style="display:inline;">
            <input type="hidden" name="contentNo" value="${boardDetail.contentNo}">
            <button name="sxubmit" type="submit" class="btn delete-btn" onclick="return confirm('정말로 삭제하시겠습니까?')">삭제</button>
        </form>
    </div>
    
    
    
    
</div>


</body>
</html>
