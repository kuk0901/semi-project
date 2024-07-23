<%--
  Created by IntelliJ IDEA.
  User: kukhahyeon
  Date: 24. 7. 17.
  Time: 오후 8:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <title>sign in</title>
  <link rel="stylesheet" href=".././src/css/common.css"/>
  <link rel="stylesheet" href="../src/css/signinPage.css"/>
  <script defer src="../src/js/index.js"></script>
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
  <div class="signin_form">
    <h2 class="signin_title">로그인</h2>
    <form action="" accept-charset="UTF-8" method="get" name="signin">
      <div class="common_signin__style">
        <input
            type="text"
            name="id"
            placeholder="id"
            class="signin_input"
        />
      </div>
      <div class="common_signin__style">
        <input
            type="password"
            name="password"
            placeholder="password"
            class="signin_input"
        />
      </div>
      <button type="submit" id="signin_btn" class="btn">로그인</button>
    </form>
    <div class="style_a">
      <a href="../signup" class="move_signup">회원가입</a>
    </div>
  </div>
</div>

<!-- footer -->
<div id="footer">
  <div class="footer_note">
    <ul class="footer_note__list">
      <li>
        <a href="">Lorem ipsum</a>
      </li>
      <li>
        <a href="">amet consectetur adipisicing</a>
      </li>
      <li>
        <a href="">고객센터</a>
      </li>
    </ul>
  </div>
</div>
</body>
</html>
