
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>새 게시글 작성</title>


<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/reset-css@5.0.2/reset.min.css">


<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/common/common.css">


<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/board/reviewboard/newReviewBoard.css">


<script type="text/javascript">
	        var contextPath = "<%= request.getContextPath() %>";
	    </script>
<script defer
	src="${pageContext.request.contextPath}/js/movePage/movePageFncs.js"></script>
<!-- JavaScript 파일 로드 -->
<script defer
	src="<%= request.getContextPath() %>/js/board/reviewboard/newReviewBoard.js"></script>
</head>

<body class="bodystyle">
	<jsp:include page="/jsp/common/header.jsp" />

	<div id="main-container" class="form-container">
		<h2 id="form-heading">새 게시글 작성</h2>
		<form action="<%= request.getContextPath() %>/board/reviewboard/add"
			method="post" enctype="multipart/form-data">
			<label for="subject" class="label">제목:</label> <input type="text"
				id="subject" name="contentSubject" class="input-field" required>

			<label class="label">본문:</label>
			<div id="contentText" contenteditable="true" class="textarea-field"></div>
			<input type="hidden" id="hiddenContentText" name="contentText">

			<label for="file" class="label">첨부 파일: .png .jpeg .jpg .gif
				.webp만 업로드 가능합니다</label> <input type="file" id="file" name="contentFile"
				class="file-field"
				accept="image/jpeg, image/jpg, image/png, image/gif, image/webp"
				multiple>
			<div id="file-list"></div>

			<button type="button" onclick="insertImageFromInput()"
				class="submit-button">이미지 본문삽입</button>

			<input type="submit" id="completeButton" value="작성 완료"
				class="submit-button">
		</form>

		<a href="<%= request.getContextPath() %>/board/reviewboard/list">목록으로
			돌아가기</a>
	</div>


	<jsp:include page="/jsp/common/footer.jsp" />
</body>
</html>
