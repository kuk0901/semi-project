package gudiSpring.comment.dto;

import java.sql.Date;

public class CommentDto {
	

    // 기본 생성자
    public CommentDto() {
    }
    private int commentNo;          // 댓글 번호
    private int contentNo;          // 게시글 번호
    private String contentComment;  // 댓글 내용
    private Date commentCreDate;    // 댓글 생성일
    private Date commentUpdateDate; // 댓글 수정일
    private int parentCommentNo; // 대댓글의 부모 댓글 번호
    private int userNo;
    private String nickname;
    
    
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public int getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}
	public int getContentNo() {
		return contentNo;
	}
	public void setContentNo(int contentNo) {
		this.contentNo = contentNo;
	}
	public String getContentComment() {
		return contentComment;
	}
	public void setContentComment(String contentComment) {
		this.contentComment = contentComment;
	}
	public Date getCommentCreDate() {
		return commentCreDate;
	}
	public void setCommentCreDate(Date commentCreDate) {
		this.commentCreDate = commentCreDate;
	}
	public Date getCommentUpdateDate() {
		return commentUpdateDate;
	}
	public void setCommentUpdateDate(Date commentUpdateDate) {
		this.commentUpdateDate = commentUpdateDate;
	}
	public int getParentCommentNo() {
		return parentCommentNo;
	}
	public void setParentCommentNo(int parentCommentNo) {
		this.parentCommentNo = parentCommentNo;
	}
	
	
	public CommentDto(int commentNo, int contentNo, String contentComment, Date commentCreDate, Date commentUpdateDate,
			int parentCommentNo, int userNo) {
		super();
		this.commentNo = commentNo;
		this.contentNo = contentNo;
		this.contentComment = contentComment;
		this.commentCreDate = commentCreDate;
		this.commentUpdateDate = commentUpdateDate;
		this.parentCommentNo = parentCommentNo;
		this.userNo = userNo;
	}
	@Override
	public String toString() {
		return "CommentDto [commentNo=" + commentNo + ", contentNo=" + contentNo + ", contentComment=" + contentComment
				+ ", commentCreDate=" + commentCreDate + ", commentUpdateDate=" + commentUpdateDate
				+ ", parentCommentNo=" + parentCommentNo + ", userNo=" + userNo + ", nickname=" + nickname + "]";
	}
	public CommentDto(int commentNo, int contentNo, String contentComment, Date commentCreDate, Date commentUpdateDate,
			int parentCommentNo) {
		super();
		this.commentNo = commentNo;
		this.contentNo = contentNo;
		this.contentComment = contentComment;
		this.commentCreDate = commentCreDate;
		this.commentUpdateDate = commentUpdateDate;
		this.parentCommentNo = parentCommentNo;
	}
	public String getNickname() {
		return nickname;
	}
	public CommentDto(int commentNo, int contentNo, String contentComment, Date commentCreDate, Date commentUpdateDate,
			int parentCommentNo, int userNo, String nickname) {
		super();
		this.commentNo = commentNo;
		this.contentNo = contentNo;
		this.contentComment = contentComment;
		this.commentCreDate = commentCreDate;
		this.commentUpdateDate = commentUpdateDate;
		this.parentCommentNo = parentCommentNo;
		this.userNo = userNo;
		this.nickname = nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
    


}
