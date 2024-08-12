/**
 * 
 */
// 댓글 삭제 확인
function confirmDelete(commentNo, contentNo, boardType) {
    if (confirm("정말로 이 댓글을 삭제하시겠습니까?")) {
        window.location.href = contextPath + '/deleteComment?commentNo=' + commentNo + '&contentNo=' + contentNo + '&boardType=' + boardType;
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
