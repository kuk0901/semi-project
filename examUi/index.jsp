<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <title>Dog With You</title>
  <link rel="stylesheet" href="./src/css/common.css"/>
  <link rel="stylesheet" href="./src/css/index.css"/>
  <script defer src="./src/js/index.js"></script>
</head>
<body>
<!-- header -->
<div id="header">
  <div id="header_logo">DwY</div>
  <div class="header_menu">
    <ul class="menu_list">
      <li class="menu_list__item">
        <a class="btn menu_list__item__btn">펜션</a>
      </li>
      <li class="menu_list__item">
        <a class="btn menu_list__item__btn">식당</a>
      </li>
      <li class="menu_list__item">
        <a class="btn menu_list__item__btn">카페</a>
      </li>
    </ul>
  </div>
  <div class="header_navigate">
    <div class="header_link">
      <a href="../signin">sign in</a>
    </div>
    <div class="header_link">
      <a href="../signup">sign up</a>
    </div>
  </div>
</div>

<!-- main content -->
<div id="main_content">
<%--  <div class="main_content_bg"></div>--%>
  <div class="main_content__img"></div>
<%--  <div class="main_content_bg"></div>--%>
</div>

<!-- footer -->
<div id="footer">
  <div class="footer_caution">
    <div class="footer_caution_title">주의사항</div>
    <ul class="footer_caution__List">
      <li>
        <span>Dog With You에서 사용된 이미지는 해당 장소와 다를 수 있습니다.</span>
      </li>
      <li>
        <span>리스트에 소개되는 장소는 Dog With You와 어떠한 이해관계도 없으며, Dog With You가 보증하는 가게가 아닙니다.</span>
      </li>
      <li>
        <span>본 페이지에 공개된 리스트는 추후 업데이트 될 수 있으며, 업데이트 되는 가게 정보는 저장해둔 리스트에 자동으로 반영됩니다.</span>
      </li>
      <li>
        <span>Dog With You는 사전 공지 없이 변경, 종료될 수 있습니다.</span>
      </li>
      <li>
        <span>비방 목적으로 작성된 댓글은 임의로 삭제될 수 있습니다.</span>
      </li>
      <li>
        <span>기타 문의사항은 고객센터를 통해 문의 부탁드립니다.</span>
      </li>
    </ul>
  </div>
  <div class="footer_note">
    <ul class="footer_note__list">
      <li>
        <a href="">Lorem ipsum</a>
      </li>
      <li>
        <a href="">amet consectetur</a>
      </li>
      <li>
        <a href="">고객센터</a>
      </li>
    </ul>
  </div>
</div>
</body>
</html>