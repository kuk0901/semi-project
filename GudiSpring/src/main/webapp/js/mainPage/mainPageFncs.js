/**
 * 
 */

/*랜덤 장소 추천 카테고리 스와이프기능*/
 new Swiper('.swiper', {
	  autoplay: {
	    delay: 5000
	  },
	  loop: true,
	  slidesPerView: 5,
	  spaceBetween: 10,
	  centeredSlides: true,
	  pagination: {
	    el: '.swiper-pagination',
	    clickable: true
	  },
	  navigation: {
	    prevEl: '.swiper-button-prev',
	    nextEl: '.swiper-button-next'
	  }
	})