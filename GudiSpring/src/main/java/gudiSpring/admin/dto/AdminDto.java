package gudiSpring.admin.dto;

public class AdminDto {
	private int userNo;
	private String name;
	private String nickname;
	private String id;
	private String pwd;
	private String phone;
	private String creDate;
	private String upDate;
	private String authority;

	//관리자 페이지 전용
	private int postCount;
	private int commentCount;
	
	public AdminDto() {
		super();
	}

	public AdminDto(int userNo, String id, String pwd) {
		super();
		this.userNo = userNo;
		this.id = id;
		this.pwd = pwd;
	}

	public AdminDto(int userNo, String name, String id, String pwd, String authority) {
		super();
		this.userNo = userNo;
		this.name = name;
		this.id = id;
		this.pwd = pwd;
		this.authority = authority;
	}
	
	public AdminDto(int userNo, String name, String nickname, String id, String creDate, String authority,
	    int postCount, int commentCount) {
		super();
		this.userNo = userNo;
		this.name = name;
		this.nickname = nickname;
		this.id = id;
		this.creDate = creDate;
		this.authority = authority;
		this.postCount = postCount;
		this.commentCount = commentCount;
	}
	
	public AdminDto(int userNo, String name, String nickname, String id, String pwd, String phone, String creDate,
	    String upDate, String authority, int postCount, int commentCount) {
		super();
		this.userNo = userNo;
		this.name = name;
		this.nickname = nickname;
		this.id = id;
		this.pwd = pwd;
		this.phone = phone;
		this.creDate = creDate;
		this.upDate = upDate;
		this.authority = authority;
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
		return "AdminDto [userNo=" + userNo + ", name=" + name + ", nickname=" + nickname + ", id=" + id + ", pwd=" + pwd
		    + ", phone=" + phone + ", creDate=" + creDate + ", upDate=" + upDate + ", authority=" + authority
		    + ", postCount=" + postCount + ", commentCount=" + commentCount + "]";
	}
}
