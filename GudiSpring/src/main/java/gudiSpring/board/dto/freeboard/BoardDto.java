package gudiSpring.board.dto.freeboard;

import java.sql.Date;

public class BoardDto {

	private int contentNo; // 게시글 번호
	private String contentSubject; // 게시글 제목
	private String contentText; // 게시글 내용
	private String contentFile; // 첨부 파일
	private int contentBoardInfoNo; // 게시판 정보 번호
	private Date contentCreDate; // 작성일
	private Date contentUpdateDate; // 수정일
	private int userNo; // 사용자 번호

	public BoardDto() {
		super();
	}

	public BoardDto(int contentNo, String contentSubject,int contentBoardInfoNo, Date contentCreDate, int userNo) {
		super();
		this.contentNo = contentNo;
		this.contentSubject = contentSubject;
		this.contentBoardInfoNo = contentBoardInfoNo;
		this.contentCreDate = contentCreDate;
		this.userNo = userNo;
	}

	public BoardDto(int contentNo, String contentSubject, String contentText, String contentFile, int contentBoardInfoNo,
	    Date contentCreDate, Date contentUpdateDate, int userNo) {
		super();
		this.contentNo = contentNo;
		this.contentSubject = contentSubject;
		this.contentText = contentText;
		this.contentFile = contentFile;
		this.contentBoardInfoNo = contentBoardInfoNo;
		this.contentCreDate = contentCreDate;
		this.contentUpdateDate = contentUpdateDate;
		this.userNo = userNo;
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

	public String getContentFile() {
		return contentFile;
	}

	public void setContentFile(String contentFile) {
		this.contentFile = contentFile;
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

	@Override
	public String toString() {
		return "BoardDto [contentNo=" + contentNo + ", contentSubject=" + contentSubject + ", contentText=" + contentText
		    + ", contentFile=" + contentFile + ", contentBoardInfoNo=" + contentBoardInfoNo + ", contentCreDate="
		    + contentCreDate + ", contentUpdateDate=" + contentUpdateDate + ", userNo=" + userNo + "]";
	}

}
