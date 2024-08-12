package gudiSpring.user.dto;

public class UserDto {

	private int userNo;
	private String name;
	private String nickName;
	private String id;
	private String pwd;
	private String phone;
	private String creDate;
	private String upDate;
	private String authority;
	private String userLeave;
	private int postCount;
	private int commentCount;

	public UserDto() {
		super();
	}

	public UserDto(int userNo, String id, String pwd, String authority) {
		super();
		this.userNo = userNo;
		this.id = id;
		this.pwd = pwd;
		this.authority = authority;
	}

	public UserDto(int userNo, String name, String id, String pwd, String authority) {
		super();
		this.userNo = userNo;
		this.name = name;
		this.id = id;
		this.pwd = pwd;
		this.authority = authority;
	}

	public UserDto(String userName, String nickName, String id, String pwd, String userPhone) {
		this.name = userName;
		this.nickName = nickName;
		this.id = id;
		this.pwd = pwd;
		this.phone = userPhone;
	}

	public UserDto(int userNo, String name, String nickName, String id, String creDate, String authority,
	    String userLeave, int postCount, int commentCount) {
		super();
		this.userNo = userNo;
		this.name = name;
		this.nickName = nickName;
		this.id = id;
		this.creDate = creDate;
		this.authority = authority;
		this.userLeave = userLeave;
		this.postCount = postCount;
		this.commentCount = commentCount;
	}

	public UserDto(int userNo, String name, String nickName, String id, String pwd, String phone, String creDate,
	    String upDate, String authority, String userLeave, int postCount, int commentCount) {
		super();
		this.userNo = userNo;
		this.name = name;
		this.nickName = nickName;
		this.id = id;
		this.pwd = pwd;
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

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
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
		return "UserDto [userNo=" + userNo + ", name=" + name + ", nickName=" + nickName + ", id=" + id + ", pwd=" + pwd
		    + ", phone=" + phone + ", creDate=" + creDate + ", upDate=" + upDate + ", authority=" + authority
		    + ", userLeave=" + userLeave + ", postCount=" + postCount + ", commentCount=" + commentCount + "]";
	}

}
