package gudiSpring.user.dto;

import java.sql.Date;

public class UserDto {

	private int userNo;
	private String name;
	private String nickname;
	private String id;
	private String password;
	private String phone;
	private String creDate;
	private String upDate;
	private String authority;
	private String userLeave;
	
	//마이페이지 조회 전용
	private int contentNo;
	private String contentSubject; 
	private Date contentCreDate;
	
	// 관리자 페이지 전용
	private int postCount;
	private int commentCount;

	public UserDto() {
		super();
	}

	public UserDto(int userNo, String id, String password, String authority) {
		super();
		this.userNo = userNo;
		this.id = id;
		this.password = password;
		this.authority = authority;
	}

	public UserDto(String userName, String nickname, String id, String userPassWord, String userPhone) {
		super();
		this.name = userName;
		this.nickname = nickname;
		this.id = id;
		this.password = userPassWord;
		this.phone = userPhone;
	}
	
	public UserDto(int userNo, String name, String id, String password, String authority) {
		super();
		this.userNo = userNo;
		this.name = name;
		this.id = id;
		this.password = password;
		this.authority = authority;
	}

	public UserDto(int userNo, String name, String nickname, String id, String password, String phone,
			String authority, String userLeave) {
		super();
		this.userNo = userNo;
		this.name = name;
		this.nickname = nickname;
		this.id = id;
		this.password = password;
		this.phone = phone;
		this.authority = authority;
		this.userLeave = userLeave;
	}
	
	public UserDto(int userNo, String name, String nickname, String id, String creDate, String authority,
	    String userLeave, int postCount, int commentCount) {
		super();
		this.userNo = userNo;
		this.name = name;
		this.nickname = nickname;
		this.id = id;
		this.creDate = creDate;
		this.authority = authority;
		this.userLeave = userLeave;
		this.postCount = postCount;
		this.commentCount = commentCount;
	}

	public UserDto(int userNo, String name, String nickname, String id, String pwd, String phone, String creDate,
	    String upDate, String authority, String userLeave, int postCount, int commentCount) {
		super();
		this.userNo = userNo;
		this.name = name;
		this.nickname = nickname;
		this.id = id;
		this.password = pwd;
		this.phone = phone;
		this.creDate = creDate;
		this.upDate = upDate;
		this.authority = authority;
		this.userLeave = userLeave;
		this.postCount = postCount;
		this.commentCount = commentCount;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCreDate() {
		return creDate;
	}

	public void setCreDate(String creDate) {
		this.creDate = creDate;
	}

	public String getUpDate() {
		return upDate;
	}

	public void setUpDate(String upDate) {
		this.upDate = upDate;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getUserLeave() {
		return userLeave;
	}

	public void setUserLeave(String userLeave) {
		this.userLeave = userLeave;
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

	public Date getContentCreDate() {
		return contentCreDate;
	}

	public void setContentCreDate(Date contentCreDate) {
		this.contentCreDate = contentCreDate;
	}

	public int getPostCount() {
		return postCount;
	}

	public void setPostCount(int postCount) {
		this.postCount = postCount;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	@Override
	public String toString() {
		return "UserDto [userNo=" + userNo + ", name=" + name + ", nickname=" + nickname + ", id=" + id + ", password="
		    + password + ", phone=" + phone + ", creDate=" + creDate + ", upDate=" + upDate + ", authority=" + authority
		    + ", userLeave=" + userLeave + ", contentNo=" + contentNo + ", contentSubject=" + contentSubject
		    + ", contentCreDate=" + contentCreDate + ", postCount=" + postCount + ", commentCount=" + commentCount + "]";
	}
	
	//글 작성 권한이 있는지 확인하는 메서드
  public boolean hasAdminPermission() {
      return "admin".equals(this.authority);
  }

}
