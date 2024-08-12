package gudiSpring.admin.dto;

public class AdminDto {
	private int userNo;
	private String name;
	private String nickname;
	private String id;
	private String password;
	private String phone;
	private String creString;
	private String upString;
	private String authority;

	public AdminDto() {
		super();
	}

	public AdminDto(int userNo, String name, String id, String password, String authority) {
		super();
		this.userNo = userNo;
		this.name = name;
		this.id = id;
		this.password = password;
		this.authority = authority;
	}

	public AdminDto(int userNo, String name, String nickname, String id, String password, String phone, String creString,
	    String upString, String authority) {
		super();
		this.userNo = userNo;
		this.name = name;
		this.nickname = nickname;
		this.id = id;
		this.password = password;
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
		return "AdminDto [userNo=" + userNo + ", name=" + name + ", nickname=" + nickname + ", id=" + id + ", password=" + password
		    + ", phone=" + phone + ", creString=" + creString + ", upString=" + upString + ", authority=" + authority + "]";
	}
}
