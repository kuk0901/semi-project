/**
 * 
 */
  function validateFileInput() {
            const fileInput = document.getElementById('file');
            const filePath = fileInput.value;
            const allowedExtensions = /(\.jpg|\.jpeg|\.png|\.gif|\.webp)$/i;

            if (!allowedExtensions.exec(filePath)) {
                alert('이미지 파일만 업로드 가능합니다. (jpg, jpeg, png, gif, webp)');
                fileInput.value = '';
                return false;
            }
            return true;
        }
        function deleteFile(fileName, contentNo) {
            if (confirm("정말로 이 파일을 삭제하시겠습니까?")) {
                fetch(`${window.location.origin}${window.location.pathname}/deleteFile`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        contentNo: contentNo,
                        fileName: fileName
                    })
                })
                .then(response => response.json())
                .then(data => {
                    if (data.success) {
                        document.getElementById(`file-${fileName}`).remove();
                    } else {
                        alert('파일 삭제에 실패했습니다.');
                    }
                })
                .catch(error => {
                    console.error('파일 삭제 중 오류가 발생했습니다.', error);
                    alert('파일 삭제 중 오류가 발생했습니다.');
                });
            }
        }