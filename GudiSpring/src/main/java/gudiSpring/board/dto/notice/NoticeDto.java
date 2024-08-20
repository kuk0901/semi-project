package gudiSpring.board.dto.notice;

import java.sql.Date;

public class NoticeDto {
	
	private int contentNo;
    private String contentSubject;
    private String contentText;
    private String contentFile;
    private int contentBoardInfoNo;
    private Date contentCreDate;
    private Date contentUpdateDate;
    private int userNo;
    
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
		return "NoticeDto [contentNo=" + contentNo + ", contentSubject=" + contentSubject + ", contentText="
				+ contentText + ", contentFile=" + contentFile + ", contentBoardInfoNo=" + contentBoardInfoNo
				+ ", contentCreDate=" + contentCreDate + ", contentUpdateDate=" + contentUpdateDate + ", userNo="
				+ userNo + "]";
	}
	
	
	
}
