/**
 * 
 */
function showAdminCheckForm(adminNo) {
	// adminCheckNo의 값을 클릭한 관리자의 userNo로 설정
	document.getElementById("adminCheckNo").value = adminNo;
	
	// form 필드를 보이게 함
	const adminCheckFormEl = document.getElementById("adminCheckForm");
	adminCheckFormEl.style.display = "block"; // 비밀번호 입력 필드 보이기
	
	const passwordEl = document.getElementById("adminPasswordInput")
	passwordEl.focus(); // 입력 필드에 포커스
}

function submitForm() {
	const password = document.getElementById("password").value;
	
	if (password) {
		const adminCheckFormEl = document.getElementById("adminCheckForm");

		adminCheckFormEl.submit(); // 폼 제출
	} else {
		alert("비밀번호를 입력하세요.");
	}
}

// click event
const existEl = document.querySelector(".adminCheckForm--exist");

existEl.addEventListener("click", (e) => {
	e.preventDefault();
	const adminCheckFormEl = document.getElementById("adminCheckForm");
	
	adminCheckFormEl.style.display = "none"; // 닫기 버튼
})