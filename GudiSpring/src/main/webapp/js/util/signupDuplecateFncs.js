/**
 * 
 */

/*닉네임 서버로 보내서 중복체크*/
function nicknameCheckAndSubmit() {
	// 가져올 els의 value
	const userNameEl = document.getElementById("userName");
	const userNicknameEl = document.getElementById("userNickname");
	const userIdEl = document.getElementById("userId");
	const userPassWordEl = document.getElementById("userPassword");
	const userPhoneEl = document.getElementById("phone");


	// value 삽입할 els
	const checkuserNameEl = document.getElementById("checkUserNameForNn");
	const checkUserNicknameEl = document.getElementById("checkNicknameForNn");
	const checkUserIdEl = document.getElementById("checkUserIdForNn");
	const checkUserPassWordEl = document.getElementById("checkUserPasswordForNn");
	const checkUserPhoneEl = document.getElementById("checkUserPhoneForNn");

	checkuserNameEl.value = userNameEl.value;
	checkUserNicknameEl.value = userNicknameEl.value;
	checkUserIdEl.value = userIdEl.value;
	checkUserPassWordEl.value = userPassWordEl.value;
	checkUserPhoneEl.value = userPhoneEl.value;

	if (document.frm.userNickname.value == "") {
		alert("아이디를 입력해주세요");
		document.frm.userNickname.focus();
		return;
	}

	document.getElementById("duplicateNicknameForm").submit();


}

/*마이페이지 닉네임 유효성 검사*/
function myPageNicknameCheckAndSubmit(){
	const userNameEl = document.getElementById("userName");
	const userNicknameEl = document.getElementById("userNickname");
	const userIdEl = document.getElementById("userId");
	const userPhoneEl = document.getElementById("phone");
	
	const checkuserNameEl = document.getElementById("checkUserNameForNn");
	const checkUserNicknameEl = document.getElementById("checkNicknameForNn");
	const checkUserIdEl = document.getElementById("checkUserIdForNn");
	const checkUserPhoneEl = document.getElementById("checkUserPhoneForNn");
	
	if(userNicknameEl.value == ""){
		alert("닉네임을 입력해주세요");
		return;
	}
	
	checkuserNameEl.value = userNameEl.value;
	checkUserNicknameEl.value = userNicknameEl.value;
	checkUserIdEl.value = userIdEl.value;
	checkUserPhoneEl.value = userPhoneEl.value;
	
	
	document.getElementById("duplicateNicknameForm").submit();

}

/*마이페이지 아이디 중복검사*/
function myPageIdCheckAndSubmit(){
	const userNameEl = document.getElementById("userName");
	const userIdEl = document.getElementById("userId");
	const userNicknameEl = document.getElementById("userNickname");
	const userPhoneEl = document.getElementById("phone");
	
	const checkuserNameEl = document.getElementById("checkUserNameForId");
	const checkUserIdEl = document.getElementById("checkUserIdForId");
	const checkUserNicknameEl = document.getElementById("checkNicknameForId");
	const checkUserPhoneEl = document.getElementById("checkUserPhoneForId");
	
	if(userIdEl.value == ""){
		alert("아이디를 입력해주세요");
		return;
	}
	
	checkuserNameEl.value = userNameEl.value;
	checkUserIdEl.value = userIdEl.value;
	checkUserNicknameEl.value = userNicknameEl.value;
	checkUserPhoneEl.value = userPhoneEl.value;
	
	document.getElementById("duplicateIdForm").submit();

}

/*아이디 서버로 보내서 중복체크*/
function idCheckAndSubmit() {
	const userNameEl = document.getElementById("userName");
	const userNicknameEl = document.getElementById("userNickname");
	const userIdEl = document.getElementById("userId");
	const userPassWordEl = document.getElementById("userPassword");
	const userPhoneEl = document.getElementById("phone");

	const checkuserNameEl = document.getElementById("checkUserNameForId");
	const checkUserNicknameEl = document.getElementById("checkNicknameForId");
	const checkUserIdEl = document.getElementById("checkUserIdForId");
	const checkUserPassWordEl = document.getElementById("checkUserPasswordForId");
	const checkUserPhoneEl = document.getElementById("checkUserPhoneForId");

	checkuserNameEl.value = userNameEl.value;
	checkUserNicknameEl.value = userNicknameEl.value;
	checkUserIdEl.value = userIdEl.value;
	checkUserPassWordEl.value = userPassWordEl.value;
	checkUserPhoneEl.value = userPhoneEl.value;

	document.getElementById("duplicateIdForm").submit();

}

/*최종 DB로 보낼 회원가입 데이터*/
function signupCheckAndSubmit() {
	const userNameEl = document.getElementById("userName");
	const userIdEl = document.getElementById("userId");
	const userPasswordEl = document.getElementById("userPassword");
	const phoneEl = document.getElementById("phone");
	
        // 이름
    var regIdPw = /^[a-zA-Z0-9]{4,12}$/;
    var regName = /^[가-힣a-zA-Z]{2,15}$/;
   	var regPhone =  /^[0-9]*$/;

	//이름 유효성검사
	if(userNameEl.value == ""){
		alert("이름을 입력해주세요");
		return;
	}else if(!regName.test(userNameEl.value)){
		alert("이름은 최소 2글자 이상, 한글과 영어만 입력하세요.")
		userNameEl.focus();
		return false;
	}
	
	//비밀번호 유효성검사
	if(userPasswordEl.value == ""){
		alert("비밀번호를 입력하세요.")
		userPasswordEl.focus();
		return ;
	}else if(!regIdPw.test(userPasswordEl.value)){
		alert("비밀번호는 4~12자 영문 대소문자, 숫자만 입력하세요.")
		userPasswordEl.focus();
		return ;
	}else if(userPasswordEl.value == userIdEl.value){
        alert("아이디와 동일한 비밀번호를 사용할 수 없습니다.")
        userPasswordEl.focus();
        return;
    }
    
    if(phoneEl.value == ""){
		alert("휴대폰번호를 입력하세요.")
		phoneEl.focus();
		return ;
	}else if(!regPhone.test(phoneEl.value)){
		alert("휴대폰번호는 숫자만 입력해주세요.")
		phoneEl.focus();
		return ;
	}


	document.getElementById("mainSignupForm").submit();

}

function updateCheckAndSubmit(){
	const userPhoneEl = document.getElementById("phone");
	
	var regPhone =  /^[0-9]*$/;
	
	if(userPhoneEl.value == ""){
		alert("휴대폰번호를 입력하세요.")
		userPhoneEl.focus();
		return ;
	}else if(!regPhone.test(userPhoneEl.value)){
		alert("휴대폰번호는 숫자만 입력해주세요.")
		userPhoneEl.focus();
		return ;
	}
	
	document.getElementById("updateForm").submit();
	
}

/*패스워드 일치 검사*/
function passWordRetypeFnc() {
	const userPassWordEl = document.getElementById("userPassword");
	const checkPassWordEl = document.getElementById("userPassword-retype");
	const PassWordError = document.getElementById("passWordError");
	const passWordSuccess = document.getElementById("passWordSuccess");

	if (userPassWordEl.value != checkPassWordEl.value) {
		PassWordError.innerText = "비밀번호가 일치하지않습니다.";
		passWordSuccess.innerText = "";
		return;
	} else {
		PassWordError.innerText = "";
		passWordSuccess.innerText = "비밀번호가 일치합니다.";
	}
}

/*에러메세지 숨기기*/
document.addEventListener('click', function() {
	// class가 'errorMessage'인 모든 span 요소 선택
	const spanElements = document.querySelectorAll('.errorMessage');

	// 각 span 요소의 텍스트를 빈 문자열로 설정하여 사라지게 함
	spanElements.forEach(function(spanElement) {
		spanElement.textContent = '';
	});
});

