/**
 * 
 */
  function confirmDelete(commentNo, contentNo, boardType) {
        if (confirm("정말로 이 댓글을 삭제하시겠습니까?")) {
            window.location.href = contextPath+'/comment/delete?commentNo=' + commentNo + '&contentNo=' + contentNo + '&boardType=' + boardType;
        }
    }
        function validateForm(form) {
            var content = form.commentContent.value.trim();
            if (content === '') {
                alert('댓글 내용을 입력하세요.');
                return false;
            }
            return true;
        }
        function openEditForm(commentNo) {
            var form = document.getElementById('editForm-' + commentNo);
            form.style.display = form.style.display === 'none' ? 'block' : 'none';
        }
        function confirmDeletePost(contentNo) {
            if (confirm("정말로 이 게시글을 삭제하시겠습니까?")) {
                window.location.href = contextPath+'/board/customerserviceboard/delete?contentNo=' + contentNo;
            }
        }