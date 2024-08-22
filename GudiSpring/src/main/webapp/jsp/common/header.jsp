<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="header">

	<div class="logo-container">
		<a class="logo-container--logo"
			href="${pageContext.request.contextPath}">DwY</a>
	</div>

	<div class="category-container">
		<ul>

			<!-- 펜션 -->
			<li>
				<button
					onclick="movePensionAreaPageFnc('${pageContext.request.contextPath}');"
					class="btn btn--primary">
					<span>펜션</span>
				</button>
				<ul>
					<li><a
						href="${pageContext.request.contextPath}/area/place/pension?areaNo=1">경기도</a></li>
					<li><a
						href="${pageContext.request.contextPath}/area/place/pension?areaNo=2">강원도</a></li>
					<li><a
						href="${pageContext.request.contextPath}/area/place/pension?areaNo=3">충청도</a></li>
					<li><a
						href="${pageContext.request.contextPath}/area/place/pension?areaNo=4">전라도</a></li>
					<li><a
						href="${pageContext.request.contextPath}/area/place/pension?areaNo=5">경상도</a></li>
					<li><a
						href="${pageContext.request.contextPath}/area/place/pension?areaNo=6">제주도</a></li>
				</ul>
			</li>

			<!-- 식당 -->
			<li>
				<button
					onclick="moveRestaurantAreaPageFnc('${pageContext.request.contextPath}');"
					class="btn btn--primary">
					<span>식당</span>
				</button>
				<ul>
					<li><a
						href="${pageContext.request.contextPath}/area/place/restaurant?areaNo=1">경기도</a></li>
					<li><a
						href="${pageContext.request.contextPath}/area/place/restaurant?areaNo=2">강원도</a></li>
					<li><a
						href="${pageContext.request.contextPath}/area/place/restaurant?areaNo=3">충청도</a></li>
					<li><a
						href="${pageContext.request.contextPath}/area/place/restaurant?areaNo=4">전라도</a></li>
					<li><a
						href="${pageContext.request.contextPath}/area/place/restaurant?areaNo=5">경상도</a></li>
					<li><a
						href="${pageContext.request.contextPath}/area/place/restaurant?areaNo=6">제주도</a></li>
				</ul>
			</li>

			<!-- 카페 -->
			<li>
				<button
					onclick="moveCafeAreaPageFnc('${pageContext.request.contextPath}');"
					class="btn btn--primary">
					<span>카페</span>
				</button>
				<ul>
					<li><a
						href="${pageContext.request.contextPath}/area/place/cafe?areaNo=1">경기도</a></li>
					<li><a
						href="${pageContext.request.contextPath}/area/place/cafe?areaNo=2">강원도</a></li>
					<li><a
						href="${pageContext.request.contextPath}/area/place/cafe?areaNo=3">충청도</a></li>
					<li><a
						href="${pageContext.request.contextPath}/area/place/cafe?areaNo=4">전라도</a></li>
					<li><a
						href="${pageContext.request.contextPath}/area/place/cafe?areaNo=5">경상도</a></li>
					<li><a
						href="${pageContext.request.contextPath}/area/place/cafe?areaNo=6">제주도</a></li>
				</ul>
			</li>

			<!-- 게시판 -->
			<li>
				<button
					onclick="moveFreeBoardListPageFnc('${pageContext.request.contextPath}');"
					class="btn btn--primary btn--lg">

					<span>게시판</span>
				</button>
				<ul>
					<li><a href="${pageContext.request.contextPath}/board/notice/list">공지사항</a></li>
					<li><a
						href="${pageContext.request.contextPath}/board/freeboard/list">자유게시판</a></li>
					<li><a
						href="${pageContext.request.contextPath}/board/reviewboard/list">리뷰게시판</a></li>
					<li><a href="${pageContext.request.contextPath}/board/customerserviceboard/list">고객센터</a></li>
				</ul>
			</li>
		</ul>
	</div>

	<!-- user data 확인 -->
	<c:if test="${sessionScope.userDto == null}">
		<div class="auth-container">
			<div class="auth-container--btn">
				<button id="auth_signin" class="btn btn--auth btn--left"
					onclick="moveSigninPageFnc('${pageContext.request.contextPath}');">SignIn</button>
			</div>
			<div class="auth-container--btn">
				<button id="auth_signup" class="btn btn--auth"
					onclick="moveSignupPageFnc('${pageContext.request.contextPath}');">SignUp</button>
			</div>
		</div>
	</c:if>

	<c:if test="${sessionScope.userDto.authority eq 'user'}">
		<div class="auth-container">
			<button id="user_page" class="btn btn--auth btn--left"
				onclick="moveUserDetailPageFnc('${pageContext.request.contextPath}', '${sessionScope.userDto.userNo}');">My</button>
			<button id="auth_signout" class="btn btn--auth btn__signout"
				onclick="moveLogoutPageFnc('${pageContext.request.contextPath}');">SignOut</button>
		</div>
	</c:if>
</div>