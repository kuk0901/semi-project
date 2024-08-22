<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Event list</title>
<link rel="stylesheet"
  href="https://cdn.jsdelivr.net/npm/reset-css@5.0.2/reset.min.css">
<link rel="stylesheet"
  href="${pageContext.request.contextPath}/css/common/common.css"
  type="text/css" />
<link rel="stylesheet"
  href="${pageContext.request.contextPath}/css/event/eventDetail.css"
  type="text/css" />
<script defer
  src="${pageContext.request.contextPath}/js/movePage/movePageFncs.js"></script>
</head>
<body>
  <jsp:include page="/jsp/common/header.jsp" />

  <div id="main-container">
    <div class="main-container__content">

      <div class="event__all-select">
        <button
          onclick="moveEventListPageFnc('${pageContext.request.contextPath}')"
          class="btn">돌아가기</button>
      </div>

      <div class="event">
        <ul>
          <li class="event--title">
            <div class="event--col text--black width--lg">이름</div>
            <div class="event--col text--black">시작일</div>
            <div class="event--col text--black">종료일</div>
            <div class="event--col text--black">당첨자 닉네임</div>
          </li>

          <li class="event--content">
            <div class="text--black width--lg">${eventDto.eventName}</div>
            <div class="text--black">${eventDto.openDate}</div>
            <div class="text--black">${eventDto.closeDate}</div>
            <div class="text--black">${eventDto.userNickname}</div>
          </li>

        </ul>
      </div>
      <!-- event__list -->

      <c:if test="${eventDto.userNickname != '이벤트가 진행 중입니다.'}">
        <div class="event-congratulation">
          <div class="event-congratulation__content">
            <span class="event__user">${eventDto.userNickname}</span>님 축하합니다! <br /> 고객센터에 본인의 이름, 닉네임,
            전화번호와 함께 당첨된 이벤트명을 문의글로 남겨주세요. 다시 한 번 축하드립니다!
          </div>
        </div>
      </c:if>


      <div class="site-guidelines">
        <div class="site-guidelines--title">이벤트 관련 안내사항</div>
        <ul class="site-guidelines__list">
          <li class="site-guidelines__list--content"><span>이벤트
              종료일 이후에 당첨자를 확인하실 수 있습니다.</span></li>
          <li class="site-guidelines__list--content"><span>
              이벤트에 당첨되신 분들은 고객센터에 당첨자 이름, 닉네임, 아이디, 전화번호로 문의글 남겨주시길
              바랍니다. </span></li>
          <li class="site-guidelines__list--content"><span>이벤트
              관련 기타 문의사항은 고객센터를 통해 문의 부탁드립니다.</span></li>
        </ul>
      </div>
    </div>

    <!-- main-container__content -->
  </div>
  <!-- main-container -->

  <jsp:include page="/jsp/common/footer.jsp" />
</body>
</html>