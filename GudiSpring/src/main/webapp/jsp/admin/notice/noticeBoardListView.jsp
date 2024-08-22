<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>공지사항 관리자용</title>
  <!-- Reset CSS -->
	    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reset-css@5.0.2/reset.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/common/common.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/admin/notice/noticeBoardList.css">
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
		<h1>공지사항 목록</h1>
		<!-- 글쓰기 버튼 -->
		<div class="buttons">
			<a href="${pageContext.request.contextPath}/admin/notice/add"
				class="btn-write">글쓰기</a>
		</div>
		<table>
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>내용</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>수정일</th>
					<th>관리</th>

				</tr>
			</thead>
			<tbody>
				<c:forEach var="notice" items="${noticeList}">
					<tr>
						<td>${notice.contentNo}</td>
						<td><a
							href="${pageContext.request.contextPath}/admin/notice/detail?contentNo=${notice.contentNo}"
							class="abtn">${notice.contentSubject}</a></td>
						<td>${notice.contentText}</td>
						<td>${notice.nickname}</td>
						<td>${notice.contentCreDate}</td>
						<td>${notice.contentUpdateDate}</td>
						<td>
							<!-- 수정 버튼 --> <a
							href="${pageContext.request.contextPath}/admin/notice/edit?contentNo=${notice.contentNo}"
							class="abtn">수정</a> <!-- 삭제 버튼 --> <a
							href="${pageContext.request.contextPath}/admin/notice/delete?contentNo=${notice.contentNo}"
							class="abtn">삭제</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
</body>
</html>
