package gudiSpring.board.dto.reviewboard;

import java.util.Date;
import java.util.List;

public class ReviewBoardDto {
	
	    private int contentNo;               // 게시글 번호
	    private String contentSubject;       // 게시글 제목
	    private String contentText;          // 게시글 내용
	    private List<String> contentFiles; // 여러 파일 경로를 저장하는 리스트
	    private int contentBoardInfoNo;      // 게시판 정보 번호
	    private Date contentCreDate;         // 작성일
	    private Date contentUpdateDate;      // 수정일
	    private int userNo;                  // 사용자 번호
	    private String nickname; 			 //닉네임
	    private String boardInfoName;
	    
	    public String getBoardInfoName() {
			return boardInfoName;
		}

		public void setBoardInfoName(String boardInfoName) {
			this.boardInfoName = boardInfoName;
		}

		public String getNickname() {
			return nickname;
		}

		public void setNickname(String nickname) {
			this.nickname = nickname;
		}

		public ReviewBoardDto() {
			// TODO Auto-generated constructor stub
		}
	    
		public int getContentNo() {
			return contentNo;
		}
		public void setContentNo(int contentNo) {
			this.contentNo = contentNo;
		}
		public String getContentSubject() {
			return contentSubject;
		}
		public void setContentSubject(String contentSubject) {
			this.contentSubject = contentSubject;
		}
		public String getContentText() {
			return contentText;
		}
		public void setContentText(String contentText) {
			this.contentText = contentText;
		}
		
		public int getContentBoardInfoNo() {
			return contentBoardInfoNo;
		}
		public void setContentBoardInfoNo(int contentBoardInfoNo) {
			this.contentBoardInfoNo = contentBoardInfoNo;
		}
		public Date getContentCreDate() {
			return contentCreDate;
		}
		public void setContentCreDate(Date contentCreDate) {
			this.contentCreDate = contentCreDate;
		}
		public Date getContentUpdateDate() {
			return contentUpdateDate;
		}
		public void setContentUpdateDate(Date contentUpdateDate) {
			this.contentUpdateDate = contentUpdateDate;
		}
		public int getUserNo() {
			return userNo;
		}
		public void setUserNo(int userNo) {
			this.userNo = userNo;
		}

		public List<String> getContentFiles() {
			return contentFiles;
		}

		public void setContentFiles(List<String> contentFiles) {
			this.contentFiles = contentFiles;
		}

		

		@Override
		public String toString() {
			return "ReviewBoardDto [contentNo=" + contentNo + ", contentSubject=" + contentSubject + ", contentText="
					+ contentText + ", contentFiles=" + contentFiles + ", contentBoardInfoNo=" + contentBoardInfoNo
					+ ", contentCreDate=" + contentCreDate + ", contentUpdateDate=" + contentUpdateDate + ", userNo="
					+ userNo + ", nickname=" + nickname + ", boardInfoName=" + boardInfoName + "]";
		}

		public ReviewBoardDto(int contentNo, String contentSubject, String contentText, List<String> contentFiles,
				int contentBoardInfoNo, Date contentCreDate, Date contentUpdateDate, int userNo) {
			super();
			this.contentNo = contentNo;
			this.contentSubject = contentSubject;
			this.contentText = contentText;
			this.contentFiles = contentFiles;
			this.contentBoardInfoNo = contentBoardInfoNo;
			this.contentCreDate = contentCreDate;
			this.contentUpdateDate = contentUpdateDate;
			this.userNo = userNo;
		}

		public ReviewBoardDto(int contentNo, String contentSubject, String contentText, List<String> contentFiles,
				int contentBoardInfoNo, Date contentCreDate, Date contentUpdateDate, int userNo, String nickname,
				String boardInfoName) {
			super();
			this.contentNo = contentNo;
			this.contentSubject = contentSubject;
			this.contentText = contentText;
			this.contentFiles = contentFiles;
			this.contentBoardInfoNo = contentBoardInfoNo;
			this.contentCreDate = contentCreDate;
			this.contentUpdateDate = contentUpdateDate;
			this.userNo = userNo;
			this.nickname = nickname;
			this.boardInfoName = boardInfoName;
		}

		public ReviewBoardDto(int contentNo, String contentSubject, String contentText, int contentBoardInfoNo,
				Date contentCreDate, Date contentUpdateDate, int userNo, String nickname, String boardInfoName) {
			super();
			this.contentNo = contentNo;
			this.contentSubject = contentSubject;
			this.contentText = contentText;
			this.contentBoardInfoNo = contentBoardInfoNo;
			this.contentCreDate = contentCreDate;
			this.contentUpdateDate = contentUpdateDate;
			this.userNo = userNo;
			this.nickname = nickname;
			this.boardInfoName = boardInfoName;
		}

		public ReviewBoardDto(int contentNo, String contentSubject, String contentText, List<String> contentFiles,
				int contentBoardInfoNo, Date contentCreDate, Date contentUpdateDate, int userNo, String nickname) {
			super();
			this.contentNo = contentNo;
			this.contentSubject = contentSubject;
			this.contentText = contentText;
			this.contentFiles = contentFiles;
			this.contentBoardInfoNo = contentBoardInfoNo;
			this.contentCreDate = contentCreDate;
			this.contentUpdateDate = contentUpdateDate;
			this.userNo = userNo;
			this.nickname = nickname;
		}
	

}
