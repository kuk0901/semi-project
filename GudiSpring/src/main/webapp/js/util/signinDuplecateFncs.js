/**
 * 
 */

/*닉네임 서버로 보내서 중복체크*/
function signInCheckAndSubmit() {
	// 가져올 els의 value
	const userIdEl = document.getElementById("userId");
	const userPassWordEl = document.getElementById("userPassword");

	if (userIdEl.value == "") {
		alert("아이디를 입력해주세요");
		userIdEl.focus();
		return;
	}else if(userPassWordEl.value == ""){
		alert("패스워드를 입력해주세요");
		userPassWordEl.focus();
		return;
	}

	document.getElementById("loginForm").submit();
	
}

