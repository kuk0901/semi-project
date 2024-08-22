<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="admin-nav">
  
  <!-- TODO: list를 분류?
       - list 분류할 경우: 기준을 무엇으로 잡을지 고려
   -->
  <div class="nav__title" onclick="moveAdminMainPageFnc(8090);">
    ADMIN
  </div>
  
  <div class="site-info__list">
    <ul>
      <li><a href="${pageContext.request.contextPath}/admin/main" class="nav-focus">회원 관리</a></li>
      <li><a href="${pageContext.request.contextPath}/admin/place/list" class="nav-focus">장소 관리</a></li>
      <li><a href="${pageContext.request.contextPath}/admin/reservation/list" class="nav-focus">예약 현황</a></li>
      <li><a href="${pageContext.request.contextPath}/admin/event/list" class="nav-focus">이벤트 현황</a></li>
    </ul>
  </div>
  
  <div class="site-enquiry__list">
    <ul>
      <li><a href="${pageContext.request.contextPath}/admin/board/list" class="nav-focus">게시판 관리</a></li>
    </ul>
  </div>
  
  <!-- jstl 반복문 사용해서 모든 관리자 정보 확인 -->
  <div class="admin-info__list">
    <ul>
      <li><a href="${pageContext.request.contextPath}/admin/list" class="">관리자 정보</a></li>
    </ul>
  </div>

  <div class="admin-signout">
    <ul>
      <li><a href="${pageContext.request.contextPath}/admin/signout">Sign Out</a></li>
    </ul>
  </div>


</div>
