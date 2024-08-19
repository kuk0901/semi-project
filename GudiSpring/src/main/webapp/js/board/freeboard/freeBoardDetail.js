/**
 * 
 */
// 댓글 삭제 확인
function confirmDelete(commentNo, contentNo, boardType) {
    if (confirm("정말로 이 댓글을 삭제하시겠습니까?")) {
        window.location.href = contextPath + '/comment/delete?commentNo=' + commentNo + '&contentNo=' + contentNo + '&boardType=' + boardType;
    }
}

// 댓글 수정 폼 토글
function openEditForm(commentNo) {
    var form = document.getElementById('editForm-' + commentNo);
    form.style.display = form.style.display === 'none' ? 'block' : 'none';
}

// 게시글 삭제 확인
function confirmDeletePost(contentNo) {
    if (confirm("정말로 이 게시글을 삭제하시겠습니까?")) {
        window.location.href = contextPath + '/board/freeboard/delete?contentNo=' + contentNo;
    }
}

// 폼 검증
function validateForm(form) {
    var content = form.commentContent.value.trim();
    if (content === '') {
        alert('댓글 내용을 입력하세요.');
        return false;
    }
    return true;
}


//여기부터 무한댓글구현
// 초기 설정
let currentPage = 1;
let isLoading = false; // 데이터 로드 중인지 상태 확인

// 페이지 로드 시 초기 데이터 가져오기
document.addEventListener("DOMContentLoaded", () => {
    loadComments(currentPage);
});

// 스크롤 이벤트를 감지하여 무한 스크롤 구현
window.addEventListener("scroll", () => {
    if (window.innerHeight + window.scrollY >= document.body.offsetHeight - 100 && !isLoading) {
        currentPage++;
        loadComments(currentPage);
    }
});

// 댓글 데이터를 서버에서 가져오는 함수
function loadComments(page) {
    isLoading = true; // 데이터 로드 중으로 상태 변경

    fetch(`${contextPath}/board/reviewboard/list?page=${page}`)
        .then(response => response.json())
        .then(data => {
            appendComments(data.comments);
            isLoading = false; // 데이터 로드 완료 후 상태 변경
        })
        .catch(error => {
            console.error("댓글 로드 중 오류 발생:", error);
            isLoading = false;
        });
}

// 서버에서 받아온 댓글을 화면에 추가하는 함수
function appendComments(comments) {
    const commentsContainer = document.querySelector(".comments");
    comments.forEach(comment => {
        const commentElement = document.createElement("li");
        commentElement.classList.add("comment");
        commentElement.innerHTML = `
            <p><strong>댓글 번호:</strong> ${comment.commentNo}</p>
            <p><strong>내용:</strong> ${comment.contentComment}</p>
            <p><strong>작성일:</strong> ${comment.commentCreDate}</p>
            <button onclick="confirmDelete(${comment.commentNo})">삭제</button>
            <button onclick="openEditForm(${comment.commentNo})">수정</button>
        `;
        commentsContainer.appendChild(commentElement);
    });
}
