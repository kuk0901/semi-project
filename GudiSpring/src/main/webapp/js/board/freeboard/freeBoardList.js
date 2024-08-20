/**
 * 
 */


    // 공지사항 페이지로 이동하는 함수
    function goToNotice(contextPath) {
        window.location.href = contextPath +'/board/notice/list' 
        // 실제 공지사항 페이지 경로에 맞게 수정하세요.
    }

    // 리뷰 게시판으로 이동하는 함수
    function goToReviewBoard(contextPath) {
        window.location.href = contextPath +'/board/reviewboard/list' 
        // 실제 리뷰 게시판 페이지 경로에 맞게 수정하세요.
    }
