package gudiSpring.admin.dto;

public class AdminDto {
	private int userNo;
	private String name;
	private String nickName;
	private String id;
	private String pwd;
	private String phone;
	private String creString;
	private String upString;
	private String authority;

	public AdminDto() {
		super();
	}

	public AdminDto(int userNo, String name, String id, String pwd, String authority) {
		super();
		this.userNo = userNo;
		this.name = name;
		this.id = id;
		this.pwd = pwd;
		this.authority = authority;
	}

	public AdminDto(int userNo, String name, String nickName, String id, String pwd, String phone, String creString,
	    String upString, String authority) {
		super();
		this.userNo = userNo;
		this.name = name;
		this.nickName = nickName;
		this.id = id;
		this.pwd = pwd;
		this.phone = phone;
		this.creString = creString;
		this.upString = upString;
		this.authority = authority;
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

	public String getCreString() {
		return creString;
	}

	public void setCreString(String creString) {
		this.creString = creString;
	}

	public String getUpString() {
		return upString;
	}

	public void setUpString(String upString) {
		this.upString = upString;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public String toString() {
		return "AdminDto [userNo=" + userNo + ", name=" + name + ", nickName=" + nickName + ", id=" + id + ", pwd=" + pwd
		    + ", phone=" + phone + ", creString=" + creString + ", upString=" + upString + ", authority=" + authority + "]";
	}
}
