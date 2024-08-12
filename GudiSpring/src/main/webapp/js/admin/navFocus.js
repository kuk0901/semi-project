/**
 * 보류
 */

const navAEls = document.querySelectorAll(".nav-focus");

let currentFocus = null;

navAEls.forEach(navAEl => {
	navAEl.addEventListener("click", () => {
		
		if (currentFocus) {
			currentFocus.classList.remove("site-info__list--focus");
		}
	
		navAEl.classList.add("site-info__list--focus");
		currentFocus = navAEl;
	})
	
	
})

