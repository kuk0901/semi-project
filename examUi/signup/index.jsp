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
  <title>sign up</title>
  <link rel="stylesheet" href="../src/css/common.css"/>
  <link rel="stylesheet" href="../src/css/signupPage.css"/>
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
  <div class="signup_form">
    <h2 class="signup_title">회원가입</h2>
    <form action="" accept-charset="UTF-8" method="post" name="signup">
      <div class="common_signup_style">
        <h3>
          <%--@declare id="signup_id"--%><label for="signup_id">아이디</label>
        </h3>
        <input type="text" name="signup_id" class="common_signup_input" />
      </div>
      <div class="common_signup_style">
        <h3>
          <%--@declare id="signup_password"--%><label for="signup_password">비밀번호</label>
        </h3>
        <input
            type="password"
            name="signup_password"
            class="common_signup_input"
        />
      </div>
      <div class="common_signup_style">
        <h3>
          <%--@declare id="signup_password_check"--%><label for="signup_password_check">비밀번호 재확인</label>
        </h3>
        <input
            type="password"
            name="signup_password_check"
            class="common_signup_input"
        />
      </div>
      <div class="common_signup_style">
        <h3>
          <%--@declare id="signup_name"--%><label for="signup_name">이름</label>
        </h3>
        <input
            type="text"
            name="signup_name"
            class="common_signup_input"
        />
      </div>
      <div class="common_signup_style">
        <h3>
          <%--@declare id="signup_email"--%><label for="signup_email">이메일</label>
        </h3>
        <input
            type="email"
            name="signup_email"
            class="common_signup_input"
        />
      </div>
      <button type="submit" id="signup_btn" class="btn">가입하기</button>
    </form>
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
