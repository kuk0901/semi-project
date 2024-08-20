/**
 * 
 */
document.addEventListener("DOMContentLoaded", () => {
	const toastEl = document.getElementById("toast");
	const msg = toastEl.getAttribute("data-message");
	
	// 메시지가 있는 경우 토스트 메시지 표시
	if (msg) {
		showToast(msg);
	}
});

function showToast(msg) {
	const toastEl = document.getElementById("toast");
	toastEl.innerHTML = msg;
	toastEl.className = "toast show";
	
	setTimeout(() => {
		toastEl.classList.remove("show");
	}, 3000); // 3초 후 자동 완료
}

